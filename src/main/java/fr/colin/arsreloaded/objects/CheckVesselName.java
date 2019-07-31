package fr.colin.arsreloaded.objects;

import fr.colin.arsreloaded.ARSReloaded;
import fr.colin.arsreloaded.utils.DatabaseWrapper;

import java.sql.SQLException;

public class CheckVesselName {

    private String vesselid;
    private String coid;
    private String text;

    public CheckVesselName(String vesselid, String coid, String text) {
        this.vesselid = vesselid;
        this.coid = coid;
        this.text = text;
    }

    public boolean update() {
        DatabaseWrapper w = ARSReloaded.getWrapper();
        try {
            if (!w.isCo(vesselid, coid)) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        text = text.replace("\n", "\\n");
        w.changeVesselDefaultReport(vesselid, text);
        return false;
    }
}
