package com.ec.facilitator.site.reset;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.UploadModel;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;
import com.ec.facilitator.base.util.WebConfig;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

@RestController
@RequestMapping("/file")
public class FileRestController {
	@Autowired
	WebConfig webConfig;


	/**
	 * 上传文件
	 * @param file
	 * @param type
	 * @return
	 * @throws Exception
	 * @return ResponseEntity<?>
	 * @author 张荣英
	 * @date 2017年4月18日 下午5:39:26
	 */
	@AuthTag
	@SSLTag
	@RequestMapping(value = "/upload.json", method = RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam(name = "file") MultipartFile file, @RequestParam(value = "type", required = false) String type) throws Exception {
		UploadModel uploadModel = new UploadModel();
		String contentType = file.getContentType();
		MultiValueMap<String, String> headers = new HttpHeaders();
		List<String> list = new ArrayList<>();
		list.add("text/html");
		headers.put("Content-Type", list);
		File saveFile = null;
		boolean isImg = false;
		if (contentType.startsWith("image")) {
			isImg = true;
			saveFile = new File(webConfig.getImgUploadHost());
		} else {
			saveFile = new File(webConfig.getFileUploadHost());
		}

		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		String filename = file.getOriginalFilename();
		String finalFileName = getFileName(filename);
		FileOutputStream out = new FileOutputStream(new File(saveFile, finalFileName));
		byte[] bytes = file.getBytes();
		out.write(bytes);
		if (isImg) {
			uploadModel.setUrl(webConfig.getImgHost());
		} else {
			uploadModel.setUrl(webConfig.getFileHost());
		}
		String url = uploadModel.getUrl() + finalFileName;
		uploadModel.setSuccess(true);
		uploadModel.setUrl(url);
		uploadModel.setFilename(finalFileName);
		uploadModel.setImageName(finalFileName);
		out.close();
		return new ResponseEntity<UploadModel>(uploadModel, headers, HttpStatus.OK);
	}

	private String getFileName(String filename) {
		String finalFileName = "";
		String splits[] = filename.split("\\.");
		if (splits.length > 1) {
			String ext = splits[splits.length - 1];
			for (int i = 0; i < splits.length - 1; i++) {
				finalFileName += splits[i] + ".";
			}
			finalFileName = finalFileName.substring(0, finalFileName.length() - 1);
			finalFileName = finalFileName + "-" + System.currentTimeMillis() + "." + ext;
		} else {
			finalFileName = filename + "-" + System.currentTimeMillis();
		}
		finalFileName = finalFileName.replaceAll("[\\s&'+#%]", "-");
		return finalFileName;
	}

	/**
	 * 删除文件
	 * @param requestModel
	 * @return
	 * @throws IOException
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @date 2017年4月18日 下午5:38:05
	 */
	@AuthTag
	@SSLTag
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public @ResponseBody BooleanResultModel fileDelete(@RequestBody UploadModel requestModel) throws IOException {
		BooleanResultModel result = new BooleanResultModel();
		// String fileName = requestModel.getFilename();
		// File file = new File(webConfig.getFarmLogFileUploadHost(),fileName);
		// if (file.exists()) {
		// boolean delete = file.delete();
		// if (delete) {
		// File imageFile = new File(webConfig.getPdfPicUploadHost()
		// +fileName.substring(0, fileName.lastIndexOf("-"))+"/");
		// if (imageFile.exists()) {
		// deleteDir(imageFile);
		// }
		// } else {
		// result.setResult(false);
		// return result;
		// }
		// } else {
		// result.setResult(false);
		// return result;
		// }
		result.setResult(true);
		return result;
	}

	/**
	 * PDF转图片
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月18日 下午5:37:56
	 */
	public String pdfToJPG(String fileName) throws Exception {
		String imgName = "";
		String url = webConfig.getFileUploadHost() + fileName;
		File file = new File(url);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);
		int num = pdffile.getNumPages();
		for (int i = 1; i <= num; i++) {
			String name = fileName.substring(0, fileName.lastIndexOf("-") + 1) + System.currentTimeMillis();
			PDFPage page = pdffile.getPage(i);
			Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));
			Image img = page.getImage(rect.width, rect.height, rect, null, true, true);
			BufferedImage tag = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, null);
			File imageFile = new File(webConfig.getImgUploadHost());
			if (!imageFile.exists()) {
				imageFile.mkdirs();
			}
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(webConfig.getImgUploadHost() + name + ".jpg"));
			ImageIO.write(tag, "jpg", imageOutput);
			imgName = imgName + name + ".jpg" + ",";
			imageOutput.close();
		}
		raf.close();
		channel.close();
		clean(buf);
		return imgName.substring(0, imgName.length() - 1);
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void clean(final Object buffer) throws Exception {
		AccessController.doPrivileged(new PrivilegedAction() {
			@SuppressWarnings("restriction")
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
