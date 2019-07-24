package org.jqassistant.contrib.plugin.python.api.model;

import com.buschmais.jqassistant.core.store.api.model.FullQualifiedNameDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Relation.Incoming;

import java.util.List;
import java.util.Set;

import static com.buschmais.xo.neo4j.api.annotation.Relation.Outgoing;

/**
 * Describes a type.
 */
@Label(value = "Type", usingIndexedPropertyOf = FullQualifiedNameDescriptor.class)
public interface Type extends PackageMember {

    /**
     * Return the declared methods.
     *
     * @return The declared methods.
     */
    @Outgoing
    @Declares
    List<Method> getDeclaredMethods();

    /**
     * Return the declared fields.
     *
     * @return The declared fields.
     */
    @Outgoing
    @Declares
    List<Field> getDeclaredFields();

    /**
     * Return the declared members, i.e. fields and methods.
     *
     * @return The declared members.
     */
    @Outgoing
    @Declares
    List<Member> getDeclaredMembers();

    /**
     * Return the declared inner classes.
     *
     * @return The declared inner classes.
     */
    @Outgoing
    @Declares
    Set<Type> getDeclaredInnerClasses();

    @Outgoing
    List<Imports> getDependencies();

    @Incoming
    List<Imports> getDependents();

}
