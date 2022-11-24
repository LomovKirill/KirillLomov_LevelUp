package ru.levelp.at.homework7;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.levelp.at.homework7"})
@IncludeClassNamePatterns({"^.*Test$"})
public class AllTestSuite {
}
