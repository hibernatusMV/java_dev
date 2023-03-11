package de.util.helper;

public class math {
    public String deg2RA(double deg) {
        String ra = "";
        String hour = "";
        String minute = "";
        String second = "";
        int h = 0;
        int min = 0;
        double sec = 0.0f;
        double deg2 = 0.0f;
        
        deg2 = (24f/360f) * deg;
        h = (int) deg2;
        min = (int) ((deg2 - h) * 60);
        sec = ((((deg2 - h) * 60) - min) * 60);

        hour = Integer.toString(h);
        minute = Integer.toString(min);
        second = String.format("%1.1f", sec);

        ra = hour + "h " + minute + "m " + second + "s";

        return ra;
    }

    public double ra2Deg(String ra) {
        double deg = 0.0f;
        double degree = 0.0f;
        double minute = 0.0f;
        double second = 0.0f;

        degree = Double.parseDouble(ra.substring(0, 2));
        minute = Double.parseDouble(ra.substring(4, 6));

        if(ra.length() == 13) {
            second = Double.parseDouble(ra.substring(8, 12));
        } else if(ra.length() == 11) {
            second = Double.parseDouble(ra.substring(8, 10));
        }

        deg = (degree + (minute/60f) + (second/3600f))*15;

        return deg;
    }

    public String deg2dms(double deg) {
        String dms = "";
        String degree = "";
        String minute = "";
        String second = "";
        int dg = 0;
        int min = 0;
        double sec = 0.0f;
    
        dg = (int) deg;
        min = (int) ((deg - (int) deg) * 60);
        sec = ((((deg - (int) deg) * 60) - min) * 60);

        degree = Integer.toString(dg);
        minute = Integer.toString(min);
        second = String.format("%1.1f", sec);

        dms = degree + "° " + minute + "\' " + second + "\"";
                
        return dms;
    }

    public double dms2deg(String dms) {
        double deg = 0.0f;
        double degree = 0.0f;
        double minute = 0.0f;
        double second = 0.0f;

        degree = Double.parseDouble(dms.substring(0, 2));
        minute = Double.parseDouble(dms.substring(4, 6));

        if(dms.length() == 13) {
            second = Double.parseDouble(dms.substring(8, 12));
        } else if(dms.length() == 11) {
            second = Double.parseDouble(dms.substring(8, 10));
        }

        deg = degree + (minute/60f) + (second/3600f);

        return deg;
    }
}
