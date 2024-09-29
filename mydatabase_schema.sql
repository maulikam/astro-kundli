--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

--  docker exec -it postgres_db pg_dump -U myuser -d mydatabase --schema-only > mydatabase_schema.sql


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authorities; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.authorities (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.authorities OWNER TO myuser;

--
-- Name: authorities_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

ALTER TABLE public.authorities ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.authorities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: baby_names; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.baby_names (
    id uuid NOT NULL,
    gender character varying(255),
    language character varying(255),
    meaning character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.baby_names OWNER TO myuser;

--
-- Name: charts; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.charts (
    id uuid NOT NULL,
    chart_data jsonb,
    chart_type character varying(255),
    created_at timestamp(6) without time zone,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.charts OWNER TO myuser;

--
-- Name: cities; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.cities (
    id uuid NOT NULL,
    city_name character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    latitude real,
    longitude real,
    timezone character varying(255)
);


ALTER TABLE public.cities OWNER TO myuser;

--
-- Name: divisional_charts; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.divisional_charts (
    id uuid NOT NULL,
    chart_data jsonb,
    chart_type character varying(255),
    created_at timestamp(6) without time zone,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.divisional_charts OWNER TO myuser;

--
-- Name: horoscope_details; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.horoscope_details (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    horoscope_date timestamp(6) without time zone,
    language character varying(255),
    planetary_positions jsonb,
    predictions character varying(255),
    rahu_ketu_positions jsonb,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.horoscope_details OWNER TO myuser;

--
-- Name: kundlis; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.kundlis (
    id uuid NOT NULL,
    ayanamsa character varying(255),
    bhava_predictions character varying(255),
    birth_date date NOT NULL,
    birth_place character varying(255),
    birth_time time(6) without time zone NOT NULL,
    chart_data jsonb,
    created_at timestamp(6) without time zone,
    dasa_periods jsonb,
    language character varying(255),
    latitude real,
    longitude real,
    manglik_status boolean,
    nakshatra character varying(255),
    sade_sati_status boolean,
    timezone character varying(255),
    updated_at timestamp(6) without time zone,
    person_id uuid NOT NULL
);


ALTER TABLE public.kundlis OWNER TO myuser;

--
-- Name: match_making; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.match_making (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    dasa_sandhi_compatibility boolean,
    language character varying(255),
    manglik_compatibility boolean,
    nakshatra_compatibility_score integer,
    overall_compatibility_score integer,
    kundli_1_id uuid NOT NULL,
    kundli_2_id uuid NOT NULL
);


ALTER TABLE public.match_making OWNER TO myuser;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.payments (
    id uuid NOT NULL,
    amount double precision NOT NULL,
    created_at timestamp(6) without time zone,
    currency character varying(255) NOT NULL,
    payment_date timestamp(6) without time zone,
    payment_method character varying(255),
    status character varying(255),
    user_id uuid NOT NULL
);


ALTER TABLE public.payments OWNER TO myuser;

--
-- Name: persons; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.persons (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    full_name character varying(255) NOT NULL,
    gender character varying(255),
    relationship character varying(255),
    updated_at timestamp(6) without time zone,
    user_id uuid NOT NULL
);


ALTER TABLE public.persons OWNER TO myuser;

--
-- Name: planetary_positions; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.planetary_positions (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    degree real,
    planet character varying(255) NOT NULL,
    retrograde boolean,
    zodiac_sign character varying(255),
    kundli_id uuid NOT NULL
);


ALTER TABLE public.planetary_positions OWNER TO myuser;

--
-- Name: print_templates; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.print_templates (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    language character varying(255),
    name character varying(255) NOT NULL,
    template_data character varying(255)
);


ALTER TABLE public.print_templates OWNER TO myuser;

--
-- Name: reports; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.reports (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    language character varying(255),
    party_address character varying(255),
    party_name character varying(255),
    print_style character varying(255),
    report_data jsonb,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.reports OWNER TO myuser;

--
-- Name: role; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO myuser;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

ALTER TABLE public.role ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: roles_authorities; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.roles_authorities (
    role_id bigint NOT NULL,
    authority_id bigint NOT NULL
);


ALTER TABLE public.roles_authorities OWNER TO myuser;

--
-- Name: shubh_kaal; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.shubh_kaal (
    id uuid NOT NULL,
    chogadia jsonb,
    created_at timestamp(6) without time zone,
    date date,
    gulik time(6) without time zone,
    hora jsonb,
    mandi time(6) without time zone,
    rahu_kaal time(6) without time zone
);


ALTER TABLE public.shubh_kaal OWNER TO myuser;

--
-- Name: subscriptions; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.subscriptions (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    end_date date,
    plan_name character varying(255) NOT NULL,
    start_date date,
    status character varying(255),
    updated_at timestamp(6) without time zone,
    user_id uuid NOT NULL
);


ALTER TABLE public.subscriptions OWNER TO myuser;

--
-- Name: transits; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.transits (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    date date,
    planet character varying(255),
    transit_position jsonb,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.transits OWNER TO myuser;

--
-- Name: user_preferences; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.user_preferences (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    preferred_ayanamsa character varying(255),
    preferred_chart_style character varying(255),
    preferred_language character varying(255),
    updated_at timestamp(6) without time zone,
    user_id uuid NOT NULL
);


ALTER TABLE public.user_preferences OWNER TO myuser;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.user_roles (
    user_id uuid NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO myuser;

--
-- Name: users; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    email character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone_number character varying(255),
    role character varying(255),
    updated_at timestamp(6) without time zone,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO myuser;

--
-- Name: users_saved_kundlis; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.users_saved_kundlis (
    id uuid NOT NULL,
    saved_at timestamp(6) without time zone,
    kundli_id uuid NOT NULL,
    user_id uuid NOT NULL
);


ALTER TABLE public.users_saved_kundlis OWNER TO myuser;

--
-- Name: varshphal; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.varshphal (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    language character varying(255),
    predictions character varying(255),
    year integer NOT NULL,
    kundli_id uuid NOT NULL
);


ALTER TABLE public.varshphal OWNER TO myuser;

--
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);


--
-- Name: baby_names baby_names_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.baby_names
    ADD CONSTRAINT baby_names_pkey PRIMARY KEY (id);


--
-- Name: charts charts_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.charts
    ADD CONSTRAINT charts_pkey PRIMARY KEY (id);


--
-- Name: cities cities_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.cities
    ADD CONSTRAINT cities_pkey PRIMARY KEY (id);


--
-- Name: divisional_charts divisional_charts_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.divisional_charts
    ADD CONSTRAINT divisional_charts_pkey PRIMARY KEY (id);


--
-- Name: horoscope_details horoscope_details_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.horoscope_details
    ADD CONSTRAINT horoscope_details_pkey PRIMARY KEY (id);


--
-- Name: kundlis kundlis_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.kundlis
    ADD CONSTRAINT kundlis_pkey PRIMARY KEY (id);


--
-- Name: match_making match_making_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.match_making
    ADD CONSTRAINT match_making_pkey PRIMARY KEY (id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


--
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);


--
-- Name: planetary_positions planetary_positions_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.planetary_positions
    ADD CONSTRAINT planetary_positions_pkey PRIMARY KEY (id);


--
-- Name: print_templates print_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.print_templates
    ADD CONSTRAINT print_templates_pkey PRIMARY KEY (id);


--
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: roles_authorities roles_authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.roles_authorities
    ADD CONSTRAINT roles_authorities_pkey PRIMARY KEY (role_id, authority_id);


--
-- Name: shubh_kaal shubh_kaal_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.shubh_kaal
    ADD CONSTRAINT shubh_kaal_pkey PRIMARY KEY (id);


--
-- Name: subscriptions subscriptions_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.subscriptions
    ADD CONSTRAINT subscriptions_pkey PRIMARY KEY (id);


--
-- Name: transits transits_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.transits
    ADD CONSTRAINT transits_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: authorities uknb3atvjf9ov5d0egnuk47o5e; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT uknb3atvjf9ov5d0egnuk47o5e UNIQUE (name);


--
-- Name: user_preferences user_preferences_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.user_preferences
    ADD CONSTRAINT user_preferences_pkey PRIMARY KEY (id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_saved_kundlis users_saved_kundlis_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users_saved_kundlis
    ADD CONSTRAINT users_saved_kundlis_pkey PRIMARY KEY (id);


--
-- Name: varshphal varshphal_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.varshphal
    ADD CONSTRAINT varshphal_pkey PRIMARY KEY (id);


--
-- Name: divisional_charts fk1ag78b5r1i8nbdbbpaitpxeit; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.divisional_charts
    ADD CONSTRAINT fk1ag78b5r1i8nbdbbpaitpxeit FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: planetary_positions fk1qcs167ynx922k61lywpdp5io; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.planetary_positions
    ADD CONSTRAINT fk1qcs167ynx922k61lywpdp5io FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: varshphal fk7mwdkuwqsjtdplhco5fc2cn8q; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.varshphal
    ADD CONSTRAINT fk7mwdkuwqsjtdplhco5fc2cn8q FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: users_saved_kundlis fk9qjtl20bo7cdkhx9je4byh7o3; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users_saved_kundlis
    ADD CONSTRAINT fk9qjtl20bo7cdkhx9je4byh7o3 FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: match_making fkedkas4iqti52un3kxiwngp451; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.match_making
    ADD CONSTRAINT fkedkas4iqti52un3kxiwngp451 FOREIGN KEY (kundli_2_id) REFERENCES public.kundlis(id);


--
-- Name: kundlis fkem0qlappu2al6fxitk41icgw9; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.kundlis
    ADD CONSTRAINT fkem0qlappu2al6fxitk41icgw9 FOREIGN KEY (person_id) REFERENCES public.persons(id);


--
-- Name: user_preferences fkepakpib0qnm82vmaiismkqf88; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.user_preferences
    ADD CONSTRAINT fkepakpib0qnm82vmaiismkqf88 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users_saved_kundlis fkesm5ugm6uvaphdvopht4jf735; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users_saved_kundlis
    ADD CONSTRAINT fkesm5ugm6uvaphdvopht4jf735 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reports fkfb0opfvaj8a16od416pq7h42v; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT fkfb0opfvaj8a16od416pq7h42v FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: subscriptions fkhro52ohfqfbay9774bev0qinr; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.subscriptions
    ADD CONSTRAINT fkhro52ohfqfbay9774bev0qinr FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: payments fkj94hgy9v5fw1munb90tar2eje; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT fkj94hgy9v5fw1munb90tar2eje FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: roles_authorities fkkyir4v3y22xtr38aasr85n0q8; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.roles_authorities
    ADD CONSTRAINT fkkyir4v3y22xtr38aasr85n0q8 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: horoscope_details fkmbljjkbuqr5a11voptng8nnhd; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.horoscope_details
    ADD CONSTRAINT fkmbljjkbuqr5a11voptng8nnhd FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: transits fkp9193rxh74fp4je6yfjfhyx2r; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.transits
    ADD CONSTRAINT fkp9193rxh74fp4je6yfjfhyx2r FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: match_making fkqmg37siuooxrrxamje9dsbuj9; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.match_making
    ADD CONSTRAINT fkqmg37siuooxrrxamje9dsbuj9 FOREIGN KEY (kundli_1_id) REFERENCES public.kundlis(id);


--
-- Name: charts fkqtx5k2mr13shqvjksuwpp98uc; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.charts
    ADD CONSTRAINT fkqtx5k2mr13shqvjksuwpp98uc FOREIGN KEY (kundli_id) REFERENCES public.kundlis(id);


--
-- Name: user_roles fkrhfovtciq1l558cw6udg0h0d3; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: persons fkrp309masjisdm7mmqon63obpv; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT fkrp309masjisdm7mmqon63obpv FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: roles_authorities fkt69njxcgfcto5wcrd9ocmb35h; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.roles_authorities
    ADD CONSTRAINT fkt69njxcgfcto5wcrd9ocmb35h FOREIGN KEY (authority_id) REFERENCES public.authorities(id);


--
-- PostgreSQL database dump complete
--

