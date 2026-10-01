-- ============================================================
-- REFRESH_TOKENS TABLE
-- ============================================================
CREATE TABLE IF NOT EXISTS refresh_tokens (
                                              id BIGSERIAL PRIMARY KEY,
                                              user_id BIGINT NOT NULL,
                                              token VARCHAR(500) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    revoked_at TIMESTAMPTZ,
    CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
    );

-- ============================================================
-- Comments
-- ============================================================
COMMENT ON TABLE refresh_tokens IS 'Bảng lưu trữ refresh tokens cho JWT authentication';
COMMENT ON COLUMN refresh_tokens.id IS 'ID duy nhất của refresh token';
COMMENT ON COLUMN refresh_tokens.user_id IS 'ID người dùng sở hữu token';
COMMENT ON COLUMN refresh_tokens.token IS 'Chuỗi JWT refresh token';
COMMENT ON COLUMN refresh_tokens.expires_at IS 'Thời gian hết hạn của token';
COMMENT ON COLUMN refresh_tokens.created_at IS 'Thời gian tạo token';
COMMENT ON COLUMN refresh_tokens.revoked_at IS 'Thời gian thu hồi token (null nếu còn hiệu lực)';

-- ============================================================
-- Indexes
-- ============================================================
CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens(user_id);
CREATE INDEX idx_refresh_tokens_token ON refresh_tokens(token);
CREATE INDEX idx_refresh_tokens_expires_at ON refresh_tokens(expires_at);
CREATE INDEX idx_refresh_tokens_revoked_at ON refresh_tokens(revoked_at) WHERE revoked_at IS NULL;