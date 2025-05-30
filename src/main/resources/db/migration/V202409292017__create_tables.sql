-- Tables without foreign key dependencies
CREATE TABLE public.authorities (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE public.baby_names (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    gender VARCHAR(255),
    language VARCHAR(255),
    meaning VARCHAR(255),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE public.cities (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    city_name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    latitude REAL,
    longitude REAL,
    timezone VARCHAR(255)
);

CREATE TABLE public.print_templates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    language VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    template_data VARCHAR(255)
);

CREATE TABLE public.role (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE public.shubh_kaal (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chogadia JSONB,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    date DATE,
    gulik TIME(6) WITHOUT TIME ZONE,
    hora JSONB,
    mandi TIME(6) WITHOUT TIME ZONE,
    rahu_kaal TIME(6) WITHOUT TIME ZONE
);

CREATE TABLE public.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    role VARCHAR(255),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT users_role_check CHECK (role = ANY (ARRAY['USER', 'ADMIN']))
);

-- Tables with foreign key dependencies
CREATE TABLE public.persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    full_name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    relationship VARCHAR(255),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.kundlis (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ayanamsa VARCHAR(255),
    bhava_predictions VARCHAR(255),
    birth_date DATE NOT NULL,
    birth_place VARCHAR(255),
    birth_time TIME(6) WITHOUT TIME ZONE NOT NULL,
    chart_data JSONB,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    dasa_periods JSONB,
    language VARCHAR(255),
    latitude REAL,
    longitude REAL,
    manglik_status BOOLEAN,
    nakshatra VARCHAR(255),
    sade_sati_status BOOLEAN,
    timezone VARCHAR(255),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    person_id UUID NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES public.persons (id)
);

CREATE TABLE public.charts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chart_data JSONB,
    chart_type VARCHAR(255),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.divisional_charts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chart_data JSONB,
    chart_type VARCHAR(255),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.horoscope_details (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    horoscope_date TIMESTAMP(6) WITHOUT TIME ZONE,
    language VARCHAR(255),
    planetary_positions JSONB,
    predictions VARCHAR(255),
    rahu_ketu_positions JSONB,
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.match_making (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    dasa_sandhi_compatibility BOOLEAN,
    language VARCHAR(255),
    manglik_compatibility BOOLEAN,
    nakshatra_compatibility_score INTEGER,
    overall_compatibility_score INTEGER,
    kundli_1_id UUID NOT NULL,
    kundli_2_id UUID NOT NULL,
    CONSTRAINT fk_kundli_1 FOREIGN KEY (kundli_1_id) REFERENCES public.kundlis (id),
    CONSTRAINT fk_kundli_2 FOREIGN KEY (kundli_2_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    currency VARCHAR(255) NOT NULL,
    payment_date TIMESTAMP(6) WITHOUT TIME ZONE,
    payment_method VARCHAR(255),
    status VARCHAR(255),
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.planetary_positions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    degree REAL,
    planet VARCHAR(255) NOT NULL,
    retrograde BOOLEAN,
    zodiac_sign VARCHAR(255),
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.reports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    language VARCHAR(255),
    party_address VARCHAR(255),
    party_name VARCHAR(255),
    print_style VARCHAR(255),
    report_data BYTEA NOT NULL,
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.roles_authorities (
    role_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.role (id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES public.authorities (id)
);

CREATE TABLE public.subscriptions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    end_date DATE,
    plan_name VARCHAR(255) NOT NULL,
    start_date DATE,
    status VARCHAR(255),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.transits (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    date DATE,
    planet VARCHAR(255),
    transit_position JSONB,
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

CREATE TABLE public.user_preferences (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    preferred_ayanamsa VARCHAR(255),
    preferred_chart_style VARCHAR(255),
    preferred_language VARCHAR(255),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.user_roles (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.role (id)
);

CREATE TABLE public.users_saved_kundlis (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    saved_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    kundli_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.varshphal (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE DEFAULT now(),
    language VARCHAR(255),
    predictions VARCHAR(255),
    year INTEGER NOT NULL,
    kundli_id UUID NOT NULL,
    CONSTRAINT fk_kundli FOREIGN KEY (kundli_id) REFERENCES public.kundlis (id)
);

-- Create indexes for foreign key columns to improve query performance
CREATE INDEX idx_kundlis_person_id ON public.kundlis(person_id);
CREATE INDEX idx_persons_user_id ON public.persons(user_id);
CREATE INDEX idx_charts_kundli_id ON public.charts(kundli_id);
CREATE INDEX idx_divisional_charts_kundli_id ON public.divisional_charts(kundli_id);
CREATE INDEX idx_horoscope_details_kundli_id ON public.horoscope_details(kundli_id);
CREATE INDEX idx_match_making_kundli_1_id ON public.match_making(kundli_1_id);
CREATE INDEX idx_match_making_kundli_2_id ON public.match_making(kundli_2_id);
CREATE INDEX idx_payments_user_id ON public.payments(user_id);
CREATE INDEX idx_planetary_positions_kundli_id ON public.planetary_positions(kundli_id);
CREATE INDEX idx_reports_kundli_id ON public.reports(kundli_id);
CREATE INDEX idx_subscriptions_user_id ON public.subscriptions(user_id);
CREATE INDEX idx_transits_kundli_id ON public.transits(kundli_id);
CREATE INDEX idx_user_preferences_user_id ON public.user_preferences(user_id);
CREATE INDEX idx_users_saved_kundlis_kundli_id ON public.users_saved_kundlis(kundli_id);
CREATE INDEX idx_users_saved_kundlis_user_id ON public.users_saved_kundlis(user_id);
CREATE INDEX idx_varshphal_kundli_id ON public.varshphal(kundli_id);

-- Function to update the updated_at column
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ language 'plpgsql';

-- Triggers for updated_at columns
CREATE TRIGGER update_kundlis_updated_at
BEFORE UPDATE ON public.kundlis
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_persons_updated_at
BEFORE UPDATE ON public.persons
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_subscriptions_updated_at
BEFORE UPDATE ON public.subscriptions
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_user_preferences_updated_at
BEFORE UPDATE ON public.user_preferences
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_users_updated_at
BEFORE UPDATE ON public.users
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();