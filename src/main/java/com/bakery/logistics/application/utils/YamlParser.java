package com.bakery.logistics.application.utils;

import com.bakery.logistics.application.exceptions.YamlParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlParser {

  private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

  public static <T> T parse(String filename, Class<T> type) {
    try {
      return mapper.readValue(new File(filename), type);
    } catch (IOException e) {
      throw new YamlParsingException("Unable to parse file: " + filename, e);
    }
  }
}
