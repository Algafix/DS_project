package core.ds.ds_project;

import java.time.LocalDateTime;

/**
 * Not necessary understandable if necessary explain the class.
 */
public abstract class DetailedReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    public DetailedReportGeneratorVisitor(
                                        final LocalDateTime startReportDateParam,
                                        final LocalDateTime endReportDateParam) {
        super(startReportDateParam, endReportDateParam);
    }
}
