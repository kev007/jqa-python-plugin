package org.jqassistant.contrib.plugin.python.impl.scanner;

import org.jqassistant.contrib.plugin.python.antlr4.Python3Parser;

public enum RuleIndex {
    ANY              (null                             ),
    PACKAGE          (-1                               ),
    FILE             (Python3Parser.RULE_file_input    ),
    METHOD           (Python3Parser.RULE_funcdef       ),
    PARAMETER        (Python3Parser.RULE_parameters    ),
    STATEMENT        (Python3Parser.RULE_simple_stmt   ),
    IMPORT_FROM      (Python3Parser.RULE_import_from   ),
    IMPORT_AS_NAME   (Python3Parser.RULE_import_as_name),
    CLASS            (Python3Parser.RULE_classdef      );

    private Integer value;

    RuleIndex(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
