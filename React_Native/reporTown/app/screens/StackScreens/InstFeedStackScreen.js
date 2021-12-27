import * as React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import  Constants from 'expo-constants';
import InstFeedScreen from '../InstScreens/InstFeedScreen';

const InstFeedStack = createStackNavigator();

function InstFeedStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <InstFeedStack.Navigator>
      <InstFeedStack.Screen name="InstFeed" component={InstFeedScreen} options={{
            headerTitle:"Feed",
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
            ),}}/>
    </InstFeedStack.Navigator>
    </View>
  );
}

export default InstFeedStackScreen;