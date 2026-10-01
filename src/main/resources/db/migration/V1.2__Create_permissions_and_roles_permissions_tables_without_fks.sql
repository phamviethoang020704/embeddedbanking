-- Permission status enum
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'permission_status') THEN
        CREATE TYPE permission_status AS ENUM ('INACTIVE', 'ACTIVE');
    END IF;
END $$;

-- ============================================================
-- PERMISSIONS TABLE
-- ============================================================
CREATE TABLE IF NOT EXISTS permissions (
    id              BIGSERIAL PRIMARY KEY,
    menu_id         BIGINT       NOT NULL REFERENCES menus(id) ON DELETE CASCADE,
    action          VARCHAR(50)  NOT NULL,          -- VIEW, CREATE, EDIT, DELETE, APPROVE,
    code            VARCHAR(100) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(1000),
    status          permission_status NOT NULL DEFAULT 'ACTIVE',
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_permission_menu_action UNIQUE (menu_id, action)
    );

CREATE UNIQUE INDEX idx_permissions_code_active ON permissions(code);
CREATE INDEX IF NOT EXISTS idx_permissions_name ON permissions(name);

COMMENT ON TABLE  permissions                 IS 'Bảng quyền';
COMMENT ON COLUMN permissions.id              IS 'Khóa chính';
COMMENT ON COLUMN permissions.menu_id         IS 'id menu';
COMMENT ON COLUMN permissions.action          IS 'Hành động';
COMMENT ON COLUMN permissions.code            IS 'Mã quyền';
COMMENT ON COLUMN permissions.name            IS 'Tên quyền';
COMMENT ON COLUMN permissions.description     IS 'Mô tả chi tiết quyền';
COMMENT ON COLUMN permissions.status          IS 'Trạng thái: INACTIVE, ACTIVE';
COMMENT ON COLUMN permissions.created_at      IS 'Thời gian tạo';
COMMENT ON COLUMN permissions.updated_at      IS 'Thời gian cập nhật';

-- ============================================================
-- ROLES_PERMISSIONS TABLE
-- ============================================================
CREATE TABLE IF NOT EXISTS roles_permissions (
    role_id         BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    permission_id   BIGINT NOT NULL REFERENCES permissions(id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, permission_id)
);

CREATE INDEX IF NOT EXISTS idx_roles_permissions_role_id ON roles_permissions(role_id);
CREATE INDEX IF NOT EXISTS idx_roles_permissions_permission_id ON roles_permissions(permission_id);
CREATE UNIQUE INDEX idx_roles_permissions_active ON roles_permissions(role_id, permission_id);