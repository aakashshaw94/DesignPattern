package BehavioralDesignPattern;

/**
 * ✅ Definition:
 * The Template Method Pattern defines the skeleton of an algorithm in a method, deferring some steps to subclasses.
 *
 *
 * 🎯 In Simple Words:
 * 👉 Template Method = Fixed Workflow with Customizable Steps.
 * You write the overall process once (fixed steps), but allow subclasses to customize specific parts of the process.
 *
 *
 * 📦 Real-World Backend Example: Data Export Service (CSV, PDF, Excel)
 * 🔔 Problem:
 * Imagine you need to build a Data Export System.
 *
 * ✅ You want:
 *
 * A fixed process:
 *
 * Fetch Data
 *
 * Format Data
 *
 * Export File
 *
 * ✅ But you want:
 *
 * Different file types: CSV, PDF, Excel
 * Each file type should customize the formatting step.
 *
 * 👉 This is exactly where Template Method Pattern fits perfectly.
 *
 *
 *
 *
 * 💡 Real Backend Parallels:
 * Report Generators (PDF, CSV, XLSX)
 *
 * Payment Workflow Steps (validation, processing, logging)
 *
 * File Parsing/Importing Systems
 *
 * Spring Batch Jobs with reusable workflow steps
 *
 *
 *
 */
public class TemplateMethod {
    public abstract class DataExporter {

        // Template Method (final so subclasses can't override)
        public final void exportData() {
            fetchData();
            formatData();
            saveFile();
        }

        // Fixed steps
        protected void fetchData() {
            System.out.println("Fetching data from database...");
        }

        protected void saveFile() {
            System.out.println("Saving exported file...");
        }

        // Customizable step
        protected abstract void formatData();
    }
    public class CSVExporter extends DataExporter {

        @Override
        protected void formatData() {
            System.out.println("Formatting data as CSV...");
        }
    }
    public class PDFExporter extends DataExporter {

        @Override
        protected void formatData() {
            System.out.println("Formatting data as PDF...");
        }
    }
    public class ExcelExporter extends DataExporter {

        @Override
        protected void formatData() {
            System.out.println("Formatting data as Excel...");
        }
    }
    public class Main {
        public void main(String[] args) {

            DataExporter csvExporter = new CSVExporter();
            csvExporter.exportData();

            System.out.println();

            DataExporter pdfExporter = new PDFExporter();
            pdfExporter.exportData();

            System.out.println();

            DataExporter excelExporter = new ExcelExporter();
            excelExporter.exportData();
        }
    }

}
