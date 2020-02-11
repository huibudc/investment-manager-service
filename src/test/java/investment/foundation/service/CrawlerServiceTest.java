package investment.foundation.service;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

class CrawlerServiceTest {
    @Test
    void isDisallowToFetchData() {
        TestCase.assertFalse(CrawlerService.isDisallowToFetchData("http://fund.eastmoney.com/002121.html"));
        TestCase.assertTrue(CrawlerService.isDisallowToFetchData("http://fund.eastmoney.com/002121.html?spm=search"));
    }
}