package ru.levelp.at.homework2;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.levelp.at.homework2"})
@IncludeClassNamePatterns({"^.*IT$"})
public class AllTestSuite {
}
