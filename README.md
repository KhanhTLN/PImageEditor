# [PImageEditor]

## [Mô tả dự án]
Dự án này là một ứng dụng xử lý ảnh được phát triển bằng ngôn ngữ Java, hỗ trợ nhiều bộ lọc và công cụ chỉnh sửa ảnh. Ứng dụng cho phép người dùng tải lên hình ảnh, áp dụng các hiệu ứng, điều chỉnh thông số, và lưu kết quả một cách dễ dàng.

## [Mục tiêu dự án]
- Xây dựng một ứng dụng xử lý ảnh nhanh, dễ sử dụng.
- Cung cấp các công cụ chỉnh sửa cơ bản và nâng cao.
- Giao diện thân thiện, hỗ trợ tương tác trực quan với ảnh.

## [Công nghệ sử dụng]
- Ngôn ngữ lập trình: Java
- Giao diện: JavaFX, SceneBuilder
- Xử lý ảnh: BufferedImage, Graphics2D
- Lưu trữ dữ liệu: MySQL
- Lưu file: Java FileChooser

## [Cấu trúc dự án]
```java
├──  src
│   ├──  main
│   │   ├──  java
│   │   │   ├──  module-info.java
│   │   │   ├──  org
│   │   │   │   ├──  example
│   │   │   │   │   ├──  app
│   │   │   │   │   │   ├──  DataBaseConnection.java
│   │   │   │   │   │   ├──  ImageHistory.java
│   │   │   │   │   │   ├──  ImageOperation.java
│   │   │   │   │   │   ├──  ImageProcessor.java
│   │   │   │   │   │   ├──  LoginController.java
│   │   │   │   │   │   ├──  Main.java
│   │   │   │   │   │   ├──  SceneController.java
│   │   │   │   │   │   ├──  ToolsController.java
│   │   ├──  resources
│   │   │   ├──  Image
│   │   │   │   ├──  Facebook-logo-removebg-preview.png
│   │   │   │   ├──  Login-Image.jpg
│   │   │   │   ├──  X-logo-removebg-preview.png
│   │   │   │   ├──  google-logo-removebg-preview.png
│   │   │   ├──  org
│   │   │   │   ├──  example
│   │   │   │   │   ├──  app
│   │   │   │   │   │   ├──  app-UI.fxml
│   │   │   │   │   │   ├──  hello-view.fxml

```

## [Tính năng chính]
### Màn hình đăng nhập
![![alt text](image-1.png)](image.png)

### Bộ lọc ảnh
- Ta có ảnh gốc:
![alt text](INPUT.jpg)

- Bộ lọc đen trắng (Grayscale):
![alt text](image-2.png)

- Làm mờ (Blur):
![alt text](image-3.png)

- Bộ lọc âm bản (Negative):
![alt text](image-4.png)

- Bộ lọc nổi khối (Emboss):
![alt text](image-5.png) 

... và nhiều bộ lọc khác

### [Chỉnh sửa ảnh]
- Xoay ảnh:
![alt text](image-6.png)

- Lật ảnh:
![alt text](image-7.png)

- Điều chỉnh độ sáng bằng thanh điều chỉnh:
![alt text](image-8.png)

... và nhiều thao tác khác 

### [Quản lý ảnh]
- Mở ảnh từ máy tính:
![alt text](image-9.png)

- Lưu ảnh:
![alt text](image-10.png)

- Thao tác Undo/Redo:
![alt text](image-11.png)

### [Liên hệ]
- Tác giả: Trần Lê Nam Khánh
- Email: tranlenamkhanh2k5@gmail.com
@PImageEditor