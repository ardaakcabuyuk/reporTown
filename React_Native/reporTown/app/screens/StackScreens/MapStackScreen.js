import * as React from 'react';
import { TouchableOpacity, View, Dimensions} from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import  Constants from 'expo-constants';
import MapScreen from '../MapScreen';

const MapStack = createStackNavigator();

function MapStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <MapStack.Navigator>
      <MapStack.Screen name="Map" component={MapScreen} options={{
            headerTitle:"Map",
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
    </MapStack.Navigator>
    </View>
  );
}

export default MapStackScreen;