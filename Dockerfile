# Используем базовый образ с Java
FROM openjdk:17-jdk

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем все файлы из папки target в контейнер
COPY build/libs/product-manager-0.0.1-SNAPSHOT.jar /app

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "product-manager-0.0.1-SNAPSHOT.jar"]
