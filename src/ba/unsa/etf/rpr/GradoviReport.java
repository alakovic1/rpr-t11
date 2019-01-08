package ba.unsa.etf.rpr;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class GradoviReport extends JFrame{

    public void showReport(Connection conn) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/gradovi.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }

    public void saveAs(String format, Connection conn, String filePath) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/gradovi.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        switch (format) {
            case "PDF":
                JasperExportManager.exportReportToPdfFile(print, filePath);
                break;
            case "DOCX":
                JRDocxExporter export = new JRDocxExporter();
                export.setExporterInput(new SimpleExporterInput(print));
                export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(filePath)));
                SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
                export.setConfiguration(config);
                export.exportReport();
                break;
            case "XML":
                JasperExportManager.exportReportToXmlFile(print, filePath, true);
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public void showReportDrzava(Connection conn, String drzava) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/izvjestajnovi.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        parameters.put("drzavaParam", drzava);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}
