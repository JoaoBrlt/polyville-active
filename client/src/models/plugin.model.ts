export interface Plugin {
  pluginId: string;
  pluginDescription: string;
  version: string;
  requires: string;
  provider: string;
  license: string;
  pluginState: string;
}
