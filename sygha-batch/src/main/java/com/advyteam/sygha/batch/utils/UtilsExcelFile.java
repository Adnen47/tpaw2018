package com.advyteam.sygha.batch.utils;

import java.util.*;

public final class UtilsExcelFile {
    private UtilsExcelFile() {
    }

    public static Map<String, Integer> mapping = new HashMap<>();
    public static List<Header> headers;
    public static List<String> newRow;

    public static final int PERIOPAI = 0;

    public static final int SOCIETE = 1;

    public static final int ETABLISSEMENT_SIRET = 2;

    public static final int ETABLISSEMENT_Libelle = 3;

    public static final int TYPE_DECLARATION = 4;

    public static final int FRACTION = 5;

    public static final int SIREN_ETA = 6;

    public static final int NIC_ETAB = 7;

    public static final int CODE_APET = 8;

    public static final int NUMERO_EXTENSION_NATURE_LIBELLE_VOIE = 9;

    public static final int CODE_POSTAL = 10;

    public static final int LOCALITE = 11;

    public static final int COMPLEMENT_LOCALISATION = 12;

    public static final int SERVICE_DISTRIBUTION = 13;

    public static final int EFFECTIF_FIN_PERIODE = 14;

    public static final int TYPE_RENUMERATION = 15;

    public static final int IDENTIFIANT_ORGANISM = 16;

    public static final int DATE_DEBUT_RATTACHEMENT = 17;

    public static final int DATE_FIN_RATTACHEMENT = 18;

    public static final int MONTANT_TOTAL_COTISATION = 19;

    public static final int IDENTIFIANT = 20;

    public static final int QUALIFIANT_ASSIETTE = 21;

    public static final int TAUX = 22;

    public static final int MONTANT_ASSIETTE = 23;

    public static final int MONTANT_COTISATION = 24;

    public static final int CODE_INSEE_COMMUNE = 25;

    public static final int INDEX_CTP = 26;

    public static final int LIBELLE = 27;

    public static final int TAUX_CTP = 28;

    public static final int COTISATION_CTP = 29;

    public static final int MONTANT_CTP_EXTRAIT = 33;

    public static final int ECART_CTP = 34;

    public static final int TAUX_AT = 35;

    public static final int ECART_AT = 36;

    public static final int TAUX_TRANSPORT = 37;

    public static final int ECART_TRANSPORT = 38;

    static {
        headers = new ArrayList<>(Arrays.asList(
                new Header("PERPAI", (short) 26, 2000),
                new Header("Société", (short) 26, 2000),
                new Header("Etablissement", (short) 26, 3000),
                new Header("Libellé établissement", (short) 26, 4000),
                new Header("Type de déclaration", (short) 31, 2500),
                new Header("Fraction", (short) 31, 2500),
                new Header("SIREN", (short) 31, 2500),
                new Header("NIC", (short) 31, 2500),
                new Header("Code APET", (short) 31, 2500),
                new Header("Numéro, extension, nature et libellé de la voie", (short) 31, 5000),
                new Header("Code postal", (short) 31, 2500),
                new Header("Localité", (short) 31, 2500),
                new Header("Complément de la localisation de la construction", (short) 31, 5000),
                new Header("Service de distribution, complément de localisation de la voie", (short) 31, 5000),
                new Header("Effectif de fin de période déclarée de l'établissement", (short) 31, 5000),
                new Header("Type rémunération soumise à contributions d'Assurance chômage pour expatriés", (short) 31, 5000),
                new Header("Identifiant Organisme de Protection Sociale", (short) 38, 5000),
                new Header("Date de début de période de rattachement", (short) 38, 5000),
                new Header("Date de fin de période de rattachement", (short) 38, 5000),
                new Header("Montant total de cotisations", (short) 38, 5000),
                new Header("Identifiant", (short) 23, 3000),
                new Header("Qualifiant assiette", (short) 23, 2500),
                new Header("Taux", (short) 23, 2500),
                new Header("Montant assiette", (short) 23, 3500),
                new Header("Montant de cotisation", (short) 23, 2500),
                new Header("Code Insee de la commune", (short) 23, 5000),
                new Header("Index taux CTP", (short) 27, 5000),
                new Header("Libellé", (short) 27, 5000),
                new Header("Taux", (short) 27, 2500),
                new Header("Cotisation par CTP", (short) 27, 2500),
                new Header("Commentaire", (short) 27, 2500),
                new Header("Cumul", (short) 27, 2500),
                new Header("Vérification des écarts", (short) 27, 5000),
                new Header("Montant CTP extrait", (short) 27, 5000),
                new Header("Ecart CTP", (short) 27, 2500),
                new Header("Taux AT", (short) 26, 2500),
                new Header("Ecart", (short) 26, 2500),
                new Header("Taux transport", (short) 24, 2500),
                new Header("Ecart", (short) 24, 2500)));
        newRow = new ArrayList<>(Arrays.asList("S10.G00.01.001", "S20.G00.05.001", "S21.G00.22.001", "S21.G00.23.001"));
        mapping.put("S10.G00.01.001", SOCIETE);
        mapping.put("S20.G00.05.002", TYPE_DECLARATION);
        mapping.put("S20.G00.05.003", FRACTION);
        mapping.put("S21.G00.06.001", SIREN_ETA);
        mapping.put("S21.G00.11.001", NIC_ETAB);
        mapping.put("S21.G00.11.002", CODE_APET);
        mapping.put("S21.G00.11.003", NUMERO_EXTENSION_NATURE_LIBELLE_VOIE);
        mapping.put("S21.G00.11.004", CODE_POSTAL);
        mapping.put("S21.G00.11.005", LOCALITE);
        mapping.put("S21.G00.11.006", COMPLEMENT_LOCALISATION);
        mapping.put("S21.G00.11.007", SERVICE_DISTRIBUTION);
        mapping.put("S21.G00.11.008", EFFECTIF_FIN_PERIODE);
        mapping.put("S21.G00.11.009", TYPE_RENUMERATION);
        mapping.put("S21.G00.22.001", IDENTIFIANT_ORGANISM);
        mapping.put("S21.G00.22.003", DATE_DEBUT_RATTACHEMENT);
        mapping.put("S21.G00.22.004", DATE_FIN_RATTACHEMENT);
        mapping.put("S21.G00.22.005", MONTANT_TOTAL_COTISATION);
        mapping.put("S21.G00.23.001", IDENTIFIANT);
        mapping.put("S21.G00.23.002", QUALIFIANT_ASSIETTE);
        mapping.put("S21.G00.23.003", TAUX);
        mapping.put("S21.G00.23.004", MONTANT_ASSIETTE);
        mapping.put("S21.G00.23.005", MONTANT_COTISATION);
        mapping.put("S21.G00.23.006", CODE_INSEE_COMMUNE);
    }

    public static class Header {
        private String name;
        private short color;
        private int width;

        public Header(String name, short color, int width) {
            this.name = name;
            this.color = color;
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public short getColor() {
            return color;
        }

        public void setColor(short color) {
            this.color = color;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
