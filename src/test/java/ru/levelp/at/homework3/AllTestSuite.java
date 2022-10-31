package ru.levelp.at.homework3;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.levelp.at.homework3"})
@IncludeClassNamePatterns({"^.*Test$"})
public class AllTestSuite {
}
