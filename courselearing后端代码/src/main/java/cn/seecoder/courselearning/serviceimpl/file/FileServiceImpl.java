package cn.seecoder.courselearning.serviceimpl.file;

import cn.seecoder.courselearning.exception.MyException;
import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.FileConstant;
import cn.seecoder.courselearning.util.FileHelper;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${web.file-upload-path}")
    private String path;

    //文件上传功能
    @Override
    public ResultVO<FileInfoVO> uploadFile(MultipartFile file) {

        String originalName=file.getOriginalFilename();
        String type=null;

        //如果文件名不为空并且有后缀
        if(originalName!=null && originalName.lastIndexOf(".")!=-1){

            //取拓展名为type
            type = originalName.substring(originalName.lastIndexOf(".")+1);
        }

        if(!FileConstant.FileTypeIsSupported(type)){
            return new ResultVO<>(Constant.REQUEST_FAIL,"上传失败,文件格式不支持",new FileInfoVO(originalName,type,"0"));
        }


        try {
            FileInfoVO fileInfoVO = FileHelper.save(path, file);
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "文件上传成功", fileInfoVO);
        } catch (MyException myException){
            logger.error("用于存放上传文件的文件夹不存在或创建失败，请检查路径是否有效", myException);
        } catch (IOException ioException){
            logger.error("文件复制时出错", ioException);
        }
        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
    }


    //文件下载
    @Override
    public void downloadFile(String filename,HttpServletResponse response) {
        //todo
        File file=new File(path+filename);
        if(!file.exists()){
            logger.warn("文件"+filename+"不存在");
            response.addHeader("info","file not exists");
            return;
        }

        response.reset();
        response.setStatus(200);

        String contentType= FileConstant.getContentType(filename.substring(filename.indexOf('.')+1));
        if(contentType==null){
            logger.warn(filename+"文件格式不支持");
            response.addHeader("info","file type no support");
            return;
        }

        //设置响应报文的类型
        response.setContentType(contentType);

        response.setCharacterEncoding("utf-8");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + filename );
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            logger.warn("下载"+filename+"失败",e);
            response.addHeader("info","file download failed");
            return;
        }
        logger.info("文件"+filename+"下载成功");
        //允许暴露该头信息
        response.addHeader("Access-Control-Expose-Headers", "infomation");
        response.addHeader("infomation","file download success");

        return;
    }

}
