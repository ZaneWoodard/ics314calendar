package ics314.dezesseis.calendar.constants;

import java.util.HashMap;
import java.util.Set;

public enum CalendarProperty {
    
    /*
     * There are two ways to define a new CalendarProperty
     * <NAME>("<NAME>", <global definition limit>, <valid scope list>...)
     * The method above uses <global definition limit> as the definition limit for EVERY scope in the list of scopes
     * <NAME>("<NAME>", bind(<valid scope>, <scope def limit>))
     * Above method allows you to specify the definition limit for each valid scope, explicitely
     */
    CLASSIFICATION("CLASS", 1, Component.EVENT, Component.TODO, Component.JOURNAL),
    LOCATION("LOCATION", 1, Component.EVENT, Component.TODO),
    SUMMARY("SUMMARY", 1, Component.EVENT, Component.TODO, Component.JOURNAL, Component.ALARM),
    DTSTAMP("DTSTAMP", 1, Component.EVENT, Component.TODO, Component.JOURNAL, Component.FREEBUSY),
    DTEND("DTEND", 1, Component.EVENT, Component.FREEBUSY),
    DTSTART("DTSTART", 1, Component.EVENT, Component.TODO, Component.FREEBUSY, Component.TIMEZONE),
    //SPECIAL CASE: Description can be defined unlimited times in VJOURNAL
    DESCRIPTION("DESCRIPTION",
            bind(Component.EVENT, 1), bind(Component.TODO, 1), bind(Component.ALARM, 1),
            bind(Component.JOURNAL, Integer.MAX_VALUE)),
    OTHER("OTHER", Integer.MAX_VALUE, Component.EVENT, Component.TODO, Component.JOURNAL, Component.ALARM, Component.FREEBUSY, Component.TIMEZONE);
    
    /** The name of the tag to appear in the text output */
    private String tag; 
    
    /** The Components where this property can be defined, mapped to their definition limits
     * where a definition limit is the maximum number of times the property can be listed within
     * the given scope.
     */
    private HashMap<Component, Integer> scopes;
    
    private CalendarProperty(String name, int definitionLimit, Component... validScopes) {
        this.tag = name;
        this.scopes = new HashMap<>();
        for(Component component : validScopes) {
            this.scopes.put(component, definitionLimit);
        }
    }
    
    private CalendarProperty(String name, ScopeLimitBinding... validScopes) {
        this.tag = name;
        this.scopes = new HashMap<>();
        for(ScopeLimitBinding scopeLimitBinding : validScopes) {
            this.scopes.put(scopeLimitBinding.getScope(), scopeLimitBinding.getLimit());
        }
    }
    
    public static CalendarProperty getProperty(String name) {
        try {
            return valueOf(name);
        } catch(IllegalArgumentException e) {
            return OTHER;
        }
    }

    /**
     * @return the tag that is used for text output formatting
     */
    public String getTag() {
        return tag;
    }

    /**
     * 
     * @param scope - the Component type to lookup the definition limit for
     * @return Integer - the definition limit
     */
    public Integer getDefinitionLimit(Component scope) {
        return scopes.get(scope);
    }

    /**
     * 
     * @return a set of the Components under which the property can be defined
     */
    public Set<Component> getValidScopes() {
        return scopes.keySet();
    }
    
    
    /**
     * The following class is meant to represent
     * a binding between a scope(Component) and definition limit(integer)
     *
     */
    private static class ScopeLimitBinding {
        private int limit;
        private Component scope;
        public ScopeLimitBinding(Component scope, int limit) {
            this.scope = scope;
            this.limit = limit;
        }
        public int getLimit() {
            return limit;
        }
        public Component getScope() {
            return scope;
        }
    }
    
    //Shortcut method to bind a Component and integer
    private static ScopeLimitBinding bind(Component scope, int limit) {
        return new ScopeLimitBinding(scope, limit);
    }
    
}
