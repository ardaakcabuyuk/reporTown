import * as React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import  Constants from 'expo-constants';
import MapScreen from '../MapScreen';
import SearchScreen from '../SearchScreen';

const SearchStack = createStackNavigator();

function SearchStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <SearchStack.Navigator>
      <SearchStack.Screen name="Search" component={SearchScreen} options={{
            headerTitle:"Search",
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
    </SearchStack.Navigator>
    </View>
  );
}

export default SearchStackScreen;