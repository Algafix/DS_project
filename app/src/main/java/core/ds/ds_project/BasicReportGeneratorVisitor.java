package core.ds.ds_project;

import java.time.LocalDateTime;

/**
 * Not necessary understandable if necessary explain the class.
 */
public abstract class BasicReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    public BasicReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                       final LocalDateTime endReportDateParam,
                                       final Report reportParam) {
        super(startReportDateParam, endReportDateParam, reportParam);
    }


}