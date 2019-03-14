<template>
  <div>
    <v-navigation-drawer
      :mini-variant="miniVariant"
      v-model="drawer"
      enable-resize-watcher
      fixed
      app
      dark
    >
      <v-list>
        <v-list-tile
          value="true"
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
        >
          <v-list-tile-avatar>
            <v-icon color="grey">{{ item.icon }}</v-icon>
          </v-list-tile-avatar>
          <v-list-tile-content class="white--text">
            <v-list-tile-title v-text="item.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar
      app
      scroll-off-screen
      color="#099268"
      dark
    >
      <v-toolbar-side-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title>
        <router-link :to="{ name: 'home' }" tag="span" class="link headline">{{ title }}</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
  </div>
</template>

<script>
import config from '@/config'

export default {
  name: 'AppNavigation',
  data () {
    return {
      title: config.title,
      drawer: false,
      miniVariant: false,
      items: config.navigation.views
    }
  }
}
</script>
