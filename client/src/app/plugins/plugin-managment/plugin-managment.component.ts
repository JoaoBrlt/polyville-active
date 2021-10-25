import { Component, OnInit } from '@angular/core';
import { Plugin } from '../../../models/plugin.model';
import { PluginService } from '../../../services/plugin.service';

@Component({
  selector: 'app-plugin-managment',
  templateUrl: './plugin-managment.component.html',
  styleUrls: ['./plugin-managment.component.scss']
})
export class PluginManagmentComponent implements OnInit {
  /**
   * The list of plugins.
   */
  plugins: Plugin[] = [];
  pluginColumns: string[];

  constructor(private pluginService: PluginService) {
    this.plugins = [];
    this.pluginColumns = ['pluginId', 'version', 'provider', 'pluginState', 'actions'];
  }

  ngOnInit(): void {
    // Get the plugins.
    this.pluginService
      .getPlugins()
      .subscribe((plugins) => {
        this.plugins = plugins;
      });
  }

  formatState(state: string) {
    if (state === null) {
      return 'unresolved';
    }
    return state.toLowerCase();
  }

  getStateLabel(state: string) {
    /* eslint-disable @typescript-eslint/naming-convention */
    const states: Record<string, string> = {
      null: 'Non chargé',
      CREATED: 'Créé',
      DISABLED: 'Désactivé',
      RESOLVED: 'Chargé',
      RESOLVING: 'Chargement en cours...',
      STARTED: 'Démarré',
      STARTING: 'Démarrage en cours...',
      STOPPED: 'Arrêté',
      STOPPING: 'Arrêt en cours...',
      FAILED: 'Echec'
    };

    if (state in states) {
      return states[state];
    }
    return state;
  }

  resolvePlugin(plugin: Plugin) {
    plugin.pluginState = 'RESOLVING';
    this.pluginService
      .resolvePlugin(plugin.pluginId)
      .subscribe();
  }

  startPlugin(plugin: Plugin) {
    plugin.pluginState = 'STARTING';
    this.pluginService
      .startPlugin(plugin.pluginId)
      .subscribe();
  }

  stopPlugin(plugin: Plugin) {
    plugin.pluginState = 'STOPPING';
    this.pluginService
      .stopPlugin(plugin.pluginId)
      .subscribe();
  }

  reloadPlugin(plugin: Plugin) {
    this.pluginService
      .reloadPlugin(plugin.pluginId)
      .subscribe();
  }

  resolveAllPlugins() {
    console.log('Resolving plugins...');
    this.pluginService
      .resolveAllPlugins()
      .subscribe(() => {
        console.log('Resolved all new plugins.');
      });
  }

  reloadAllPlugins() {
    console.log('Reloading plugins...');
    this.pluginService
      .reloadAllPlugins()
      .subscribe(() => {
        console.log('Reloaded all the plugins.');
      });
  }
}
