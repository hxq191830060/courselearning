package cn.seecoder.courselearning.controller.file;

import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "文件上传下载API接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;

    @ApiOperation(value = "上传文件",notes = "教师上传课件时，调用该接口")
    @PostMapping("/upload")
    public ResultVO<FileInfoVO> upload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }



    @ApiOperation(value = "下载文件",notes = "下载课件时，调用该接口")
    @PostMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) {
        //todo
        fileService.downloadFile(filename,response);
    }

}
