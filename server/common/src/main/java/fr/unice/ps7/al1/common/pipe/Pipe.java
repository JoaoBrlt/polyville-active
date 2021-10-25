package fr.unice.ps7.al1.common.pipe;

import java.util.Collection;

/**
 * A pipe is use to share object between plugins.
 * A interface (let call it A) must extend Pipe and ExtensionPoint to be used.
 *
 * Consequently, all class that implement the interface A and which annotated
 * with @Extension can be access by all plugins which knows it.
 *
 * @param <E> the type of object shared
 */
public interface Pipe<E> {
	Collection<E> getElements();
}
