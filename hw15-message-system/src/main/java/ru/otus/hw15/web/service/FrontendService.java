package ru.otus.hw15.web.service;


import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
  void getUserData(long userId, Consumer<String> dataConsumer);

  <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}
