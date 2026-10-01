-- ============================================================
-- SAMPLE DATA
-- V1.3__Insert_sample_data.sql
-- ============================================================


-- ============================================================
-- ROLES
-- ============================================================
INSERT INTO roles (id, code, name, description, status)
VALUES
    (1, 'SUPER_ADMIN', 'Super Admin', 'Quản trị toàn bộ hệ thống', 'ACTIVE'),
    (2, 'ADMIN', 'Admin', 'Quản trị hệ thống', 'ACTIVE'),
    (3, 'MANAGER', 'Manager', 'Quản lý nghiệp vụ', 'ACTIVE'),
    (4, 'STAFF', 'Staff', 'Nhân viên', 'ACTIVE'),
    (5, 'USER', 'User', 'Người dùng thông thường', 'ACTIVE');


-- ============================================================
-- USERS
-- Password ở đây chỉ là dữ liệu mẫu
-- Thực tế nên lưu BCrypt hash
-- ============================================================
INSERT INTO users (
    id,
    username,
    password,
    email,
    full_name,
    role_id,
    created_at
)
VALUES
    (1, 'superadmin', '$2y$10$RQx.3BJEYzynHMsqmCQyNebDt/D6KODOFZOyv206Gd2yXqnfuCj6a', 'superadmin@msb.com.vn', 'Nguyễn Văn Super Admin', 1, CURRENT_TIMESTAMP),
    (2, 'admin01', '$2y$10$RQx.3BJEYzynHMsqmCQyNebDt/D6KODOFZOyv206Gd2yXqnfuCj6a', 'admin01@msb.com.vn', 'Trần Văn Admin', 2, CURRENT_TIMESTAMP),
    (3, 'manager01', '$2y$10$RQx.3BJEYzynHMsqmCQyNebDt/D6KODOFZOyv206Gd2yXqnfuCj6a', 'manager01@msb.com.vn', 'Lê Văn Manager', 3, CURRENT_TIMESTAMP),
    (4, 'staff01', '$2y$10$RQx.3BJEYzynHMsqmCQyNebDt/D6KODOFZOyv206Gd2yXqnfuCj6a', 'staff01@msb.com.vn', 'Phạm Văn Staff', 4, CURRENT_TIMESTAMP),
    (5, 'user01', '$2y$10$RQx.3BJEYzynHMsqmCQyNebDt/D6KODOFZOyv206Gd2yXqnfuCj6a', 'user01@msb.com.vn', 'Hoàng Văn User', 5, CURRENT_TIMESTAMP);


-- ============================================================
-- MENUS
-- parent_id = NULL: menu cha
-- parent_id != NULL: menu con
-- ============================================================
INSERT INTO menus (
    id,
    parent_id,
    code,
    name,
    path,
    icon,
    sort_order,
    status
)
VALUES
    (1, NULL, 'DASHBOARD', 'Dashboard', '/dashboard', 'dashboard', 1, 'ACTIVE'),

    (2, NULL, 'USER_MANAGEMENT', 'Quản lý người dùng', NULL, 'users', 2, 'ACTIVE'),

    (3, 2, 'USER', 'Người dùng', '/users', 'user', 1, 'ACTIVE'),

    (4, 2, 'ROLE', 'Vai trò', '/roles', 'shield', 2, 'ACTIVE'),

    (5, NULL, 'SYSTEM_MANAGEMENT', 'Quản lý hệ thống', NULL, 'settings', 3, 'ACTIVE'),

    (6, 5, 'MENU', 'Menu', '/menus', 'menu', 1, 'ACTIVE'),

    (7, 5, 'PERMISSION', 'Quyền', '/permissions', 'lock', 2, 'ACTIVE');


-- ============================================================
-- PERMISSIONS
-- ============================================================
INSERT INTO permissions (
    id,
    menu_id,
    action,
    code,
    name,
    description,
    status
)
VALUES
    (1, 1, 'VIEW',
     'DASHBOARD_VIEW',
     'Xem Dashboard',
     'Cho phép xem Dashboard',
     'ACTIVE'),

    (2, 3, 'VIEW',
     'USER_VIEW',
     'Xem người dùng',
     'Cho phép xem danh sách người dùng',
     'ACTIVE'),

    (3, 3, 'CREATE',
     'USER_CREATE',
     'Thêm người dùng',
     'Cho phép tạo mới người dùng',
     'ACTIVE'),

    (4, 3, 'EDIT',
     'USER_EDIT',
     'Sửa người dùng',
     'Cho phép cập nhật thông tin người dùng',
     'ACTIVE'),

    (5, 3, 'DELETE',
     'USER_DELETE',
     'Xóa người dùng',
     'Cho phép xóa người dùng',
     'ACTIVE'),

    (6, 4, 'VIEW',
     'ROLE_VIEW',
     'Xem vai trò',
     'Cho phép xem danh sách vai trò',
     'ACTIVE'),

    (7, 4, 'CREATE',
     'ROLE_CREATE',
     'Thêm vai trò',
     'Cho phép tạo mới vai trò',
     'ACTIVE'),

    (8, 4, 'EDIT',
     'ROLE_EDIT',
     'Sửa vai trò',
     'Cho phép cập nhật vai trò',
     'ACTIVE'),

    (9, 6, 'VIEW',
     'MENU_VIEW',
     'Xem menu',
     'Cho phép xem danh sách menu',
     'ACTIVE'),

    (10, 7, 'VIEW',
     'PERMISSION_VIEW',
     'Xem quyền',
     'Cho phép xem danh sách quyền',
     'ACTIVE');


-- ============================================================
-- ROLES_PERMISSIONS
-- ============================================================

-- SUPER_ADMIN: có toàn bộ quyền
INSERT INTO roles_permissions (role_id, permission_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10);


-- ADMIN
INSERT INTO roles_permissions (role_id, permission_id)
VALUES
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (2, 10);


-- MANAGER
INSERT INTO roles_permissions (role_id, permission_id)
VALUES
    (3, 1),
    (3, 2),
    (3, 4),
    (3, 6);


-- STAFF
INSERT INTO roles_permissions (role_id, permission_id)
VALUES
    (4, 1),
    (4, 2),
    (4, 6);


-- USER
INSERT INTO roles_permissions (role_id, permission_id)
VALUES
    (5, 1);


-- ============================================================
-- RESET SEQUENCE
-- Vì đang insert ID thủ công
-- Nếu không reset sequence, lần insert tiếp theo có thể bị duplicate key
-- ============================================================

SELECT setval(
               pg_get_serial_sequence('roles', 'id'),
               (SELECT MAX(id) FROM roles)
       );

SELECT setval(
               pg_get_serial_sequence('users', 'id'),
               (SELECT MAX(id) FROM users)
       );

SELECT setval(
               pg_get_serial_sequence('menus', 'id'),
               (SELECT MAX(id) FROM menus)
       );

SELECT setval(
               pg_get_serial_sequence('permissions', 'id'),
               (SELECT MAX(id) FROM permissions)
       );