Nội dung chính Chương 1

Giới thiệu về Flutter

Flutter là framework đa nền tảng do Google phát triển, cho phép viết một lần và chạy trên Android, iOS, web, desktop.

Nhiều công ty lớn đã dùng Flutter như BMW, eBay, Google Pay, Toyota…

Khi nào không nên dùng Flutter

Làm game 2D/3D phức tạp → nên dùng Unity/Unreal.

Ứng dụng cần âm thanh phức tạp hoặc SDK gốc đặc thù.

App chỉ chạy trên 1 nền tảng và cần tận dụng toàn bộ công cụ gốc.

Lịch sử Flutter

Bắt nguồn từ ý tưởng cải thiện hiệu suất web của Google.

Ngôn ngữ Dart được chọn vì hỗ trợ lập trình bất đồng bộ, hướng đối tượng, tốc độ cao.

Flutter dùng Skia (engine đồ họa của Chrome, Android, Firefox) để vẽ giao diện trực tiếp trên GPU.

Từ Flutter 3.10 trở đi, iOS mặc định dùng engine mới Impeller cho hiệu năng tốt hơn

flutter-apprentice-4th

.

Kiến trúc Flutter

flutter-apprentice-4th

Gồm 3 lớp chính:

Framework (Dart): chứa widgets, layout, animation, gestures, plugins.

Engine (C++): xử lý đồ họa, văn bản, I/O, runtime Dart.

Embedder: đóng gói app cho từng nền tảng.

Thiết lập môi trường

Dùng công cụ flutter doctor để kiểm tra và cài đặt đủ dependencies (Java, Android SDK, Xcode, CocoaPods, Chrome…).

IDE khuyến nghị: Android Studio, Visual Studio Code.

Tạo dự án đầu tiên

Có thể tạo bằng IDE hoặc dòng lệnh.

Dự án mẫu là app “đếm số lần bấm nút”.

Khi chạy thành công, bạn đã có Flutter app đầu tiên.

Tóm lại:
Chương 1 giúp bạn hiểu Flutter là gì, khi nào nên/không nên dùng, kiến trúc hoạt động, lịch sử phát triển, và hướng dẫn cài đặt môi trường để chạy app Flutter đầu tiên. Đây là bước khởi động cho các chương sau về widgets, state, navigation, networking và testing.