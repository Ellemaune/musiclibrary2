package com.netcracker.musiclibrary.data;

import java.io.Serializable;
import java.util.Objects;

/** Record Genre containing fields: name
 * @param name - Name of Genre
 */
public record Genre (String name) implements Serializable {

}
