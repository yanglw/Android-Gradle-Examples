package cc.bb.aa.gradleunittest.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p></p>
 * <p>Created by yanglw on 2015-02-25 10:20.</p>
 */
public class BuildTypesTest
{
    @Test
    public void add()
    {
        Assert.assertTrue(3 == BuildTypes.addRelease(1, 2));
    }
}
