package cn.seecoder.courselearning.service.file;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    ResultVO<FileInfoVO> uploadFile(MultipartFile file);
    void downloadFile(String filename, HttpServletResponse response);
}
