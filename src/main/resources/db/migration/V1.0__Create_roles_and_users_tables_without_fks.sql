-- Role status enum
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role_status') THEN
CREATE TYPE role_status AS ENUM ('INACTIVE', 'ACTIVE');
END IF;
END $$;

-- ============================================================
-- ROLES TABLE
-- ============================================================
CREATE TABLE IF NOT EXISTS roles (
    id               BIGSERIAL PRIMARY KEY,
    code             VARCHAR(100),
    name             VARCHAR(255) NOT NULL,
    description      VARCHAR(1000),
    status           role_status NOT NULL DEFAULT 'ACTIVE',
    created_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT now()
);

COMMENT ON TABLE  roles                   IS 'Bảng vai trò';
COMMENT ON COLUMN roles.id                IS 'Khóa chính';
COMMENT ON COLUMN roles.code              IS 'Code vai trò';
COMMENT ON COLUMN roles.name              IS 'Tên vai trò';
COMMENT ON COLUMN roles.description       IS 'Mô tả chi tiết vai trò';
COMMENT ON COLUMN roles.status            IS 'Trạng thái: INACTIVE, ACTIVE';
COMMENT ON COLUMN roles.created_at        IS 'Thời gian tạo';
COMMENT ON COLUMN roles.updated_at        IS 'Thời gian cập nhật';

-- ============================================================
-- USERS TABLE
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    full_name   VARCHAR(255) NOT NULL,
    role_id     BIGINT NOT NULL DEFAULT 5,
    created_by  BIGINT,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    updated_at  TIMESTAMPTZ,
    deleted_by  BIGINT,
    deleted_at  TIMESTAMPTZ
    );

CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role_id ON users(role_id);
CREATE INDEX IF NOT EXISTS idx_users_deleted_at ON users(deleted_at) WHERE deleted_at IS NULL;

COMMENT ON TABLE  users IS 'Bảng người dùng';
COMMENT ON COLUMN users.id         IS 'Khóa chính';
COMMENT ON COLUMN users.username   IS 'Tên đăng nhập';
COMMENT ON COLUMN users.password   IS 'Mật khẩu';
COMMENT ON COLUMN users.email      IS 'Email người dùng';
COMMENT ON COLUMN users.full_name  IS 'Tên người dùng';
COMMENT ON COLUMN users.role_id    IS 'Khóa ngoại mapping với bảng vai trò';
COMMENT ON COLUMN users.created_at IS 'Thời gian tạo';
COMMENT ON COLUMN users.updated_at IS 'Thời gian cập nhật';
COMMENT ON COLUMN users.deleted_at IS 'Thời gian xóa (soft delete)';