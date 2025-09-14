-- Enables pgcrypto for gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role TEXT NOT NULL DEFAULT 'USER',
    created_at timestamptz DEFAULT now()
);

-- Projects table
CREATE TABLE IF NOT EXISTS projects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    owner_id UUID REFERENCES users(id),
    name TEXT NOT NULL,
    tagline TEXT,
    poster_key TEXT,
    created_at timestamptz DEFAULT now()
);

-- Sections table
CREATE TABLE IF NOT EXISTS sections (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

-- Seed default sections (will ignore conflicts if re-run)
INSERT INTO sections (name) VALUES
    ('Writing'),
    ('Storyboarding'),
    ('SetDiagrams'),
    ('TestRenders'),
    ('Foley'),
    ('Soundtrack'),
    ('VFX'),
    ('ColorGrading'),
    ('Legal')
ON CONFLICT (name) DO NOTHING;

