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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: journaliser(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.journaliser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE
	avant_operation text;
	apres_operation text;
	operation text;
BEGIN

	IF TG_OP = 'UPDATE' THEN
		avant_operation:= '[' || OLD.ville || ']';
		apres_operation:= '[' || NEW.ville || ']';
		operation:= 'UPDATE';
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		apres_operation:= '[' || NEW.ville || ']';
		operation:= 'INSERT';
	END IF;
	
	IF TG_OP = 'DELETE' THEN
		avant_operation:= '[' || OLD.ville || ']';
		operation:= 'DELETE';
	END IF;
	
	--apres_operation:='[' || new.ville || '] [' || new.taille || '] [' || new.habitant || '] [' || new.estcapitale ']';
	INSERT into journal(date, operation,objet,avant_operation,apres_operation) VALUES(NOW(), operation, 'lieu' ,avant_operation,apres_operation);
	RETURN new;
END
$$;


ALTER FUNCTION public.journaliser() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal (
    id integer NOT NULL,
    date timestamp with time zone NOT NULL,
    operation text NOT NULL,
    avant_operation text,
    apres_operation text,
    objet text NOT NULL
);


ALTER TABLE public.journal OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_id_seq OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_id_seq OWNED BY public.journal.id;


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
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal ALTER COLUMN id SET DEFAULT nextval('public.journal_id_seq'::regclass);


--
-- Name: lieu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu ALTER COLUMN id SET DEFAULT nextval('public.lieu_id_seq'::regclass);


--
-- Name: poisson id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poisson ALTER COLUMN id SET DEFAULT nextval('public.poisson_id_seq'::regclass);


--
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.journal (id, date, operation, avant_operation, apres_operation, objet) FROM stdin;
1	2018-09-20 10:31:39.765147-04	ajouter	poisson1	poisson2	objet
3	2018-09-20 11:00:01.255973-04	ajouter	poisson1	poisson2	objet
4	2018-09-20 11:05:44.231756-04	ajouter	[hagenau]	poisson2	objet
5	2018-09-20 11:37:06.423827-04	ajouter	\N	[hagenau]	objet
6	2018-09-20 11:37:06.423827-04	ajouter	\N	[hagenau]	objet
7	2018-09-20 11:40:13.855614-04	INSERT	\N	[uu]	lieu
8	2018-09-20 11:40:13.855614-04	INSERT	\N	[uu]	lieu
9	2018-09-20 11:41:52.152181-04	UPDATE	[uu]	[aeaeraefq]	lieu
10	2018-09-20 11:42:09.395969-04	INSERT	\N	[zf]	lieu
11	2018-09-20 11:42:09.395969-04	INSERT	\N	[zf]	lieu
12	2018-09-20 11:42:09.395969-04	INSERT	\N	[zf]	lieu
13	2018-09-20 11:43:16.695961-04	UPDATE	[zf]	[aefr]	lieu
14	2018-09-20 11:43:25.89467-04	INSERT	\N	[aze]	lieu
\.


--
-- Data for Name: lieu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lieu (id, ville, taille, habitant, estcapitale) FROM stdin;
6	Baie-Comeau	45	8234	non
1	Quebec	1546056	14323000	non
12	Otawa	12432	123345	non
15	hagenau	123	12	oui
25	hagenau	123	12	oui
14	sertyu	123	12	non
2	Matano	228	143420000	non
7	eee	1234	60532	non
26	aeaeraefq	1	2	non
27	aefr	1	3	non
28	aze	12	44	non
\.


--
-- Data for Name: poisson; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.poisson (id, nom, famille, taille, poids, id_lieu) FROM stdin;
1	saumon	salmonidée	47	453	1
2	truite	salmonidée	27	214	1
3	brochet	Esox Lucuis	86	4521	2
5	silure	siluridé	156	6542	2
13	Musky	Esox Lucius	123	6983	6
15	Perche	percide	12	13	7
16	brohet	esox lucius	87	5532	7
17	azer	AZERT	12123	55	7
\.


--
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.journal_id_seq', 14, true);


--
-- Name: lieu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lieu_id_seq', 28, true);


--
-- Name: poisson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.poisson_id_seq', 17, true);


--
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


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
-- Name: lieu evenementlieu; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementlieu BEFORE INSERT OR DELETE OR UPDATE ON public.lieu FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: poisson fk_id_lieu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.poisson
    ADD CONSTRAINT fk_id_lieu FOREIGN KEY (id_lieu) REFERENCES public.lieu(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

