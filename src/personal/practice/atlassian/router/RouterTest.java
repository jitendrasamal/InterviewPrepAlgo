package personal.practice.atlassian.router;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    @Test
    void routeExactMatch() {
        Router router = new Router();
        router.addRoute("/foo", "foo");
        assertEquals("foo", router.callRoute("/foo"));
    }

    @Test
    void routeWildcardMatch() {
        Router router = new Router();
        router.addRoute("/foo/*", "bar");
        assertEquals("bar", router.callRoute("/foo/xyz"));
    }

    @Test
    void routeExactMatchWinsOverWildcard() {
        Router router = new Router();
        router.addRoute("/foo/*", "bar");
        router.addRoute("/foo/baz", "foo");
        assertEquals("foo", router.callRoute("/foo/baz"));
    }

    @Test
    void routeNoMatch() {
        Router router = new Router();
        router.addRoute("/foo", "foo");
        assertNull(router.callRoute("/bar"));
    }

    @Test
    void routeMultipleWildcards() {
        Router router = new Router();
        router.addRoute("/*/*", "*_*");
        assertEquals("*_*", router.callRoute("/unknown/unknown"));
    }

    @Test
    void routePartialWildcardMatch() {
        Router router = new Router();
        router.addRoute("/bar/*/baz", "bar");
        assertEquals("bar", router.callRoute("/bar/a/baz"));
    }

    @Test
    void routeWildcardNoMatch() {
        Router router = new Router();
        router.addRoute("/bar/*", "bar_*");
        assertNull(router.callRoute("/bar"));
    }

    @Test
    void routeWildcardMatchWithTrailingSegment() {
        Router router = new Router();
        router.addRoute("/bar/*", "bar_*");
        assertEquals("bar_*", router.callRoute("/bar/xyz"));
    }

    @Test
    void routeWildcardNoMatchWithExtraSegments() {
        Router router = new Router();
        router.addRoute("/foo/*", "bar");
        assertNull(router.callRoute("/foo/xyz/abc"));
    }
}