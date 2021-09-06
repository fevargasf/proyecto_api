package co.gov.corantioquia.utils;

public class Main {
    public static void main(String[] args) {
        System.out.println(PropApl.getInstance().get("HOST_DIR_DOCUNET"));
        System.out.println(PropApl.getInstance().get("RUTA_DIR_DOCUNET"));//Singleton
    }
}
