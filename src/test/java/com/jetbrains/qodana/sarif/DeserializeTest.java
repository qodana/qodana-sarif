package com.jetbrains.qodana.sarif;

import com.jetbrains.qodana.sarif.model.PropertyBag;
import com.jetbrains.qodana.sarif.model.SarifReport;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DeserializeTest {
    @Test
    public void testPropertyBag() throws IOException {
        Path target = Paths.get("src/test/resources/testData/serializeTest/properties.json");
        PropertyBag bag = new PropertyBag();
        bag.put("someProp", "someValue");
        bag.put("someArray", Arrays.asList("1", "2", "3"));
        SarifReport report = new SarifReport().withProperties(bag);
        doTest(target, report);
    }

    private void doTest(Path targetJsonPath, SarifReport expected) throws IOException {
        SarifReport sarifReport = SarifUtil.readReport(targetJsonPath);
        Assert.assertEquals(expected, sarifReport);
    }
}
