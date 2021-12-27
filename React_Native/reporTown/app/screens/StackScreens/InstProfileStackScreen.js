import * as React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons, Ionicons  } from '@expo/vector-icons';
import  Constants from 'expo-constants';
import InstProfileScreen from '../InstScreens/InstProfileScreen'

const InstProfileStack = createStackNavigator();

function InstProfileStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <InstProfileStack.Navigator>
      <InstProfileStack.Screen name="InstProfile" component={InstProfileScreen} options={{
            headerTitle:"Profile",
            headerTitleAlign: "center",
            headerStyle: {
              backgroundColor: "#080f26",
              height: 50
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
            fontWeight: 'bold',
            },
            headerRight: () => (
              <TouchableOpacity onPress={() => alert('This is a button!')}>
                <MaterialCommunityIcons name="bell-alert" size={28} color="#cb7b23" style={{marginRight: 24 }}/>
              </TouchableOpacity>
            ),
            headerLeft: () => (
                <TouchableOpacity onPress={() => alert('This is a button!')}>
                  <Ionicons name="settings" size={28} color="#808080" style={{marginLeft: 24 }}/>
                </TouchableOpacity>
              ),}}/>
    </InstProfileStack.Navigator>
    </View>
  );
}

export default InstProfileStackScreen;