package com.msb.embeddedbanking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseEnum {
    UNAUTHORIZED("DSS-1", "Chưa xác thực"),
    FORBIDDEN("DSS-2", "Không có quyền truy cập"),
    INTERNAL_SERVER_ERROR("DSS-3", "Có lỗi phía server"),
    BAD_REQUEST("DSS-4", "Yêu cầu không hợp lệ"),
    NOT_FOUND("DSS-5", "Không tìm thấy"),
    CONNECTION_ERROR("DSS-6", "Có lỗi kết nối"),
    CONVERT_ERROR("DSS-7", "Có lỗi chuyển đổi dữ liệu"),
    INVALID_RESPONSE("DSS-8", "Phản hồi không hợp lệ"),
    INVALID_DATA_FORMAT("DSS-9", "Định dạng dữ liệu không hợp lệ"),
    CONSTRAINT_VIOLATION("DSS-10", "Dữ liệu vi phạm ràng buộc"),
    DATABASE_QUERY_ERROR("DSS-11", "Có lỗi truy vấn dữ liệu"),
    INVALID_CREDENTIAL("DSS-12", "Tài khoản hoặc mật khẩu không đúng"),
    MAXIMUM_LOGIN_EXCEED("DSS-13", "Số lần đăng nhập sai quá giới hạn");

    private final String code;
    private final String message;
}
