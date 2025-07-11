/*
 * Copyright (C) 2021 - 2025 Elytrium
 *
 * The LimboAPI (excluding the LimboAPI plugin) is licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in the api top-level directory.
 */

package net.elytrium.limboapi.api.material;

import com.velocitypowered.api.network.ProtocolVersion;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import net.elytrium.limboapi.api.utils.EnumUniverse;

public enum WorldVersion {
  LEGACY(EnumSet.range(ProtocolVersion.MINECRAFT_1_7_2, ProtocolVersion.MINECRAFT_1_12_2)),
  MINECRAFT_1_13(ProtocolVersion.MINECRAFT_1_13),
  MINECRAFT_1_13_2(ProtocolVersion.MINECRAFT_1_13_1, ProtocolVersion.MINECRAFT_1_13_2),
  MINECRAFT_1_14(EnumSet.range(ProtocolVersion.MINECRAFT_1_14, ProtocolVersion.MINECRAFT_1_14_4)),
  MINECRAFT_1_15(EnumSet.range(ProtocolVersion.MINECRAFT_1_15, ProtocolVersion.MINECRAFT_1_15_2)),
  MINECRAFT_1_16(ProtocolVersion.MINECRAFT_1_16, ProtocolVersion.MINECRAFT_1_16_1),
  MINECRAFT_1_16_2(EnumSet.range(ProtocolVersion.MINECRAFT_1_16_2, ProtocolVersion.MINECRAFT_1_16_4)),
  MINECRAFT_1_17(EnumSet.range(ProtocolVersion.MINECRAFT_1_17, ProtocolVersion.MINECRAFT_1_18_2)),
  MINECRAFT_1_19(EnumSet.range(ProtocolVersion.MINECRAFT_1_19, ProtocolVersion.MINECRAFT_1_19_1)),
  MINECRAFT_1_19_3(ProtocolVersion.MINECRAFT_1_19_3),
  MINECRAFT_1_19_4(EnumSet.range(ProtocolVersion.MINECRAFT_1_19_4, ProtocolVersion.MINECRAFT_1_20)),
  MINECRAFT_1_20(EnumSet.range(ProtocolVersion.MINECRAFT_1_20, ProtocolVersion.MINECRAFT_1_20_2)),
  MINECRAFT_1_20_3(ProtocolVersion.MINECRAFT_1_20_3),
  MINECRAFT_1_20_5(EnumSet.range(ProtocolVersion.MINECRAFT_1_20_5, ProtocolVersion.MINECRAFT_1_21)),
  MINECRAFT_1_21_2(ProtocolVersion.MINECRAFT_1_21_2),
  MINECRAFT_1_21_4(ProtocolVersion.MINECRAFT_1_21_4),
  MINECRAFT_1_21_5(ProtocolVersion.MINECRAFT_1_21_5),
  MINECRAFT_1_21_6(ProtocolVersion.MINECRAFT_1_21_6),
  MINECRAFT_1_21_7(EnumSet.range(ProtocolVersion.MINECRAFT_1_21_7, ProtocolVersion.MAXIMUM_VERSION));

  private static final EnumMap<ProtocolVersion, WorldVersion> MC_VERSION_TO_ITEM_VERSIONS = new EnumMap<>(ProtocolVersion.class);
  private static final Map<String, WorldVersion> KEY_LOOKUP = Map.copyOf(EnumUniverse.createProtocolLookup(values()));

  private final Set<ProtocolVersion> versions;

  WorldVersion(ProtocolVersion... versions) {
    this.versions = EnumSet.copyOf(Arrays.asList(versions));
  }

  WorldVersion(Set<ProtocolVersion> versions) {
    this.versions = versions;
  }

  public ProtocolVersion getMinSupportedVersion() {
    return this.versions.iterator().next();
  }

  public Set<ProtocolVersion> getVersions() {
    return this.versions;
  }

  static {
    for (WorldVersion version : WorldVersion.values()) {
      for (ProtocolVersion protocolVersion : version.getVersions()) {
        MC_VERSION_TO_ITEM_VERSIONS.put(protocolVersion, version);
      }
    }
  }

  public static WorldVersion parse(String from) {
    return KEY_LOOKUP.getOrDefault(from, LEGACY);
  }

  public static WorldVersion from(ProtocolVersion protocolVersion) {
    return MC_VERSION_TO_ITEM_VERSIONS.get(protocolVersion);
  }
}
