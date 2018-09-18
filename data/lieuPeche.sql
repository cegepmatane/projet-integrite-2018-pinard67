--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: lieuPeche; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "lieuPeche" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE "lieuPeche" OWNER TO postgres;

\connect "lieuPeche"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: lieu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lieu (
    id integer NOT NULL,
    ville text,
    taille integer,
    habitant integer,
    estcapitale text
);


ALTER TABLE public.lieu OWNER TO postgres;

--
-- Name: lieu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lieu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lieu_id_seq OWNER TO postgres;

--
-- Name: lieu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lieu_id_seq OWNED BY public.lieu.id;


--
-- Name: poisson; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.poisson (
    id integer NOT NULL,
    nom text,
    famille text,
    taille integer,
    poids integer,
    id_lieu integer
);


ALTER TABLE public.poisson OWNER TO postgres;

--
-- Name: poisson_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.poisson_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poisson_id_seq OWNER TO postgres;

--
-- Name: poisson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.poisson_id_seq OWNED BY public.poisson.id;


--
-- Name: lieu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu ALTER COLUMN id SET DEFAULT nextval('public.lieu_id_seq'::regclass);


--
-- Name: poisson id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poisson ALTER COLUMN id SET DEFAULT nextval('public.poisson_id_seq'::regclass);


--
-- Data for Name: lieu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lieu VALUES (6, 'Baie-Comeau', 45, 8234, 'non');
INSERT INTO public.lieu VALUES (2, 'Matane', 228, 143420000, 'non');
INSERT INTO public.lieu VALUES (1, 'Quebec', 1546056, 14323000, 'peut etre');
INSERT INTO public.lieu VALUES (7, 'Haguenau', 1234, 60532, 'non');


--
-- Data for Name: poisson; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.poisson VALUES (1, 'saumon', 'salmonidée', 47, 453, 1);
INSERT INTO public.poisson VALUES (2, 'truite', 'salmonidée', 27, 214, 1);
INSERT INTO public.poisson VALUES (3, 'brochet', 'Esox Lucuis', 86, 4521, 2);
INSERT INTO public.poisson VALUES (5, 'silure', 'siluridé', 156, 6542, 2);
INSERT INTO public.poisson VALUES (6, 'az', 'rr', 1, 3, 1);
INSERT INTO public.poisson VALUES (7, 'az', 'az', 12, 12, 1);


--
-- Name: lieu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lieu_id_seq', 9, true);


--
-- Name: poisson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.poisson_id_seq', 12, true);


--
-- Name: lieu lieu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu
    ADD CONSTRAINT lieu_pkey PRIMARY KEY (id);


--
-- Name: poisson poisson_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poisson
    ADD CONSTRAINT poisson_pkey PRIMARY KEY (id);


--
-- Name: fki_fk_id_lieu; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_id_lieu ON public.poisson USING btree (id_lieu);


--
-- Name: poisson fk_id_lieu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poisson
    ADD CONSTRAINT fk_id_lieu FOREIGN KEY (id_lieu) REFERENCES public.lieu(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

