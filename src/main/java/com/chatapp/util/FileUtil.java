package com.chatapp.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.chatapp.constant.SystemConstant;

public final class FileUtil {

  private FileUtil() {}

  public static final String folderPath =  SystemConstant.FILE_PATH_ORIGIN;
  public static final Path filePath = Paths.get(folderPath);

}
