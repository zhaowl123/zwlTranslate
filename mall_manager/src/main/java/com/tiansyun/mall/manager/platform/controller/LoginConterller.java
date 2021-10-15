package com.tiansyun.mall.manager.platform.controller;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import com.tiansyun.mall.manager.platform.entity.Translation;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZYJ
 * @Description: 英翻日
 * <p>
 * POI在读写word docx文件时是通过xwpf模块来进行的，其核心是XWPFDocument。一个XWPFDocument代表一个docx文档，其可以用来读docx文档，也可以用来写docx文档。
 * XWPFDocument中主要包含下面这几种对象：
 * XWPFParagraph：代表一个段落。
 * XWPFRun：代表具有相同属性的一段文本。
 * XWPFTable：代表一个表格。
 * XWPFTableRow：表格的一行。
 * XWPFTableCell：表格对应的一个单元格。
 * @Date: Created in 2020/11/27 16:07
 */
@Controller
public class LoginConterller {

	@RequestMapping("/login")
	public String login() throws Exception {
		return "login";
	}

	@RequestMapping("/ja")
	public void ja(@RequestParam("uploadFile") MultipartFile uploadFile, Translation translation, HttpServletRequest request, HttpServletResponse response) throws Exception {
		byte [] byteArr = uploadFile.getBytes();
		InputStream inputStream = new ByteArrayInputStream(byteArr);
		XWPFDocument doc = new XWPFDocument(inputStream);

		List<XWPFTable> tableList = doc.getTables();
		List<XWPFTableRow> rowList;
		List<XWPFTableCell> cellList;

		//获取需要翻译的最后一个表格
		XWPFTable xwpfTable = tableList.get(tableList.size() - 1);
		//获取表格相对应的行
		rowList = xwpfTable.getRows();
		System.out.println("------------------ 开始 ------------------");
		for (int i = 0; i < rowList.size(); i++) {
			if (i+1 >= translation.getStartRow()){
				try {
					XWPFTableRow xwpfTableRow = rowList.get(i);
					cellList = xwpfTableRow.getTableCells();
					//获取到需要翻译的文本
					System.out.println(cellList.get(translation.getBeTrColumn()+1).getText());
					String word = cellList.get(translation.getBeTrColumn()+1).getText();
					translation.setWord(word);
					String post = Post(translation);
					//转换json数据
					System.out.println(post);
					Gson gson = new Gson();
					List list = gson.fromJson(post, List.class);
					Map<String,Object> map = (Map<String,Object>)list.get(0);
					List<Map<String,String>> translations = (List<Map<String,String>>)map.get("translations");
					String text = translations.get(0).get("text");
					//获取到需要加入的文本
					cellList.get(translation.getTrColumn()+1).setText(text);
				} catch (Exception e) {
					e.printStackTrace();
					//continue;
				}
			}
		}

		//文件返回
		response.setContentType("application/octet-stream");
		//解决在不同浏览器下的乱码问题
		String fileName = "demo.docx";
		String userAgent=request.getHeader("user-agent");
		if (userAgent.indexOf("Firefox") != -1) {
			fileName=new String(fileName.getBytes("UTF-8"),"ISO8859-1");
		}  else if (userAgent.indexOf("Chrome") != -1)  {
			fileName=new String(fileName.getBytes("UTF-8"),"ISO8859-1");
		}else{//ie或其他等
			fileName= URLEncoder.encode(fileName, "utf-8");
		}
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);

		ServletOutputStream out = response.getOutputStream();
		doc.write(out);
		out.flush();
		out.close();
		inputStream.close();

		System.out.println("------------------ 结束 ------------------");
	}


	// This function performs a POST request.
	public String Post(Translation translation) throws IOException {
		MediaType mediaType = MediaType.parse("application/json");

		String subscriptionKey = "ef1bb771ed4142a3b12735ffefc6d390"; //key
		// Add your location, also known as region. The default is global.
		// This is required if using a Cognitive Services resource.
		String location = "eastasia"; //分区地址

		HttpUrl url = new HttpUrl.Builder()
				.scheme("https")
				.host("api.cognitive.microsofttranslator.com")
				.addPathSegment("/translate")
				.addQueryParameter("api-version", "3.0")
				.addQueryParameter("from", translation.getBeTrLanguage())
				.addQueryParameter("to", translation.getTrLanguage())
				.build();

		// Instantiates the OkHttpClient.
		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(mediaType,
				"[{\"Text\": \""+translation.getWord()+"\"}]");
		Request request = new Request.Builder().url(url).post(body)
				.addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
				.addHeader("Ocp-Apim-Subscription-Region", location)
				.addHeader("Content-type", "application/json")
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

}
