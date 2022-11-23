package ru.levelp.at.homework6;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.levelp.at.homework6"})
@IncludeClassNamePatterns({"^.*Test$"})
public class AllApiTestSuite {
}
