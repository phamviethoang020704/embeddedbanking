-- Menus status enum
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'menu_status') THEN
        CREATE TYPE menu_status AS ENUM ('INACTIVE', 'ACTIVE');
    END IF;
END $$;

-- ============================================================
-- Menu TABLE
-- ============================================================

CREATE TABLE menus (
       id              BIGSERIAL PRIMARY KEY,
       parent_id       BIGINT       REFERENCES menus(id) ON DELETE CASCADE,
       code            VARCHAR(50)  NOT NULL UNIQUE,
       name            VARCHAR(100) NOT NULL,
       path            VARCHAR(255),
       icon            VARCHAR(100),
       sort_order      INT          NOT NULL DEFAULT 0,
       status          menu_status  NOT NULL DEFAULT 'ACTIVE',
       created_at      TIMESTAMP    NOT NULL DEFAULT now(),
       updated_at      TIMESTAMP    NOT NULL DEFAULT now()
);

COMMENT ON TABLE  menus                   IS 'Bảng menu';
COMMENT ON COLUMN menus.id                IS 'Khóa chính';
COMMENT ON COLUMN menus.parent_id         IS 'parent id';
COMMENT ON COLUMN menus.code               IS 'mã';
COMMENT ON COLUMN menus.name               IS 'tên';
COMMENT ON COLUMN menus.path               IS '';
COMMENT ON COLUMN menus.icon               IS '';
COMMENT ON COLUMN menus.sort_order         IS '';
COMMENT ON COLUMN menus.status             IS 'Trạng thái : ACTIVE;INACTIVE';
COMMENT ON COLUMN menus.created_at         IS 'Thời gian tạo';
COMMENT ON COLUMN menus.updated_at         IS 'Thời gian cập nhật';