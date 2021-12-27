import * as React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import  Constants from 'expo-constants';

const FeedStack = createStackNavigator();

function FeedStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <FeedStack.Navigator>
      <FeedStack.Screen name="Feed" component={FeedScreen} options={{
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
    </FeedStack.Navigator>
    </View>
  );
}

export default FeedStackScreen;