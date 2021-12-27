import { StatusBar } from "expo-status-bar";
import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import { StyleSheet, Text, View } from "react-native";
import LoginScreen from "./app/screens/LoginScreen";
import WelcomeScreen from "./app/screens/WelcomeScreen";
import RegisterChooseScreen from "./app/screens/RegisterChooseScreen";
import CitizenRegisterScreen from "./app/screens/CitizenRegisterScreen";
import InstitutionRegisterScreen from "./app/screens/InstitutionRegisterScreen";
import SearchScreen from "./app/screens/SearchScreen";
import MapScreen from "./app/screens/MapScreen";
import PostScreen from "./app/screens/PostScreen";
import ProfileScreen from "./app/screens/ProfileScreen";
import FeedScreen from "./app/screens/FeedScreen";
import { MaterialIcons } from "@expo/vector-icons";
import CameraScreen from './app/screens/CameraScreen';
import InstFeedScreen from './app/screens/InstScreens/InstFeedScreen';
import InstMapScreen from './app/screens/InstScreens/InstMapScreen';
import InstProfileScreen from './app/screens/InstScreens/InstProfileScreen';
import InstSearchScreen from './app/screens/InstScreens/InstSearchScreen';
import EmployeeScreen from './app/screens/InstScreens/EmployeeScreen';
import FeedStackScreen from './app/screens/StackScreens/FeedStackScreen';
import MapStackScreen from './app/screens/StackScreens/MapStackScreen';
import SearchStackScreen from './app/screens/StackScreens/SearchStackScreen';
import ProfileStackScreen from './app/screens/StackScreens/ProfileStackScreen';
import PostStackScreen from './app/screens/StackScreens/PostStackScreen';
import InstProfileStackScreen from "./app/screens/StackScreens/InstProfileStackScreen";
import EmployeeStackScreen from "./app/screens/StackScreens/EmployeeStackScreen";
import InstMapStackScreen from "./app/screens/StackScreens/InstMapStackScreen";
import InstSearchStackScreen from "./app/screens/StackScreens/InstSearchStackScreen";
import InstFeedStackScreen from "./app/screens/StackScreens/InstFeedStackScreen";

const Stack = createStackNavigator();
const Tabs = createMaterialBottomTabNavigator();

const UserScreens = () => {
  return (
    <Tabs.Navigator
      initialRouteName="Feed"
      screenOptions={({ route }) => ({
        tabBarIcon: ({ color }) => {
          let iconName;

          if (route.name === "Feed") {
            iconName = "dynamic-feed";
          } else if (route.name === "Map") {
            iconName = "map";
          } else if (route.name === "Post") {
            iconName = "add";
          } else if (route.name === "Search") {
            iconName = "search";
          } else if (route.name === "Profile") {
            iconName = "person";
          }

          return <MaterialIcons name={iconName} size={24} color={color} />;
        },
      })}
      barStyle={{ backgroundColor: "#cb7b23" }}
    >
      <Tabs.Screen name="Search" component={SearchStackScreen} />
      <Tabs.Screen name="Map" component={MapStackScreen} />
      <Tabs.Screen name="Post" component={PostStackScreen} />
      <Tabs.Screen name="Feed" component={FeedStackScreen}/>
      <Tabs.Screen name="Profile" component={ProfileStackScreen} />
    </Tabs.Navigator>
  );
};

const InstScreens = () => {
  return (
    <Tabs.Navigator
      initialRouteName="Profile"
      screenOptions={({ route }) => ({
        tabBarIcon: ({ color }) => {
          let iconName;

          if (route.name === "Feed") {
            iconName = "dynamic-feed";
          } else if (route.name === "Map") {
            iconName = "map";
          } else if (route.name === "Employees") {
            iconName = "people";
          } else if (route.name === "Search") {
            iconName = "search";
          } else if (route.name === "Profile") {
            iconName = "person";
          }

          return <MaterialIcons name={iconName} size={24} color={color} />;
        },
      })}
      barStyle={{ backgroundColor: "#cb7b23" }}
    >
      <Tabs.Screen name="Search" component={InstSearchStackScreen} />
      <Tabs.Screen name="Map" component={InstMapStackScreen} />
      <Tabs.Screen name="Employees" component={EmployeeStackScreen} />
      <Tabs.Screen name="Feed" component={InstFeedStackScreen} />
      <Tabs.Screen name="Profile" component={InstProfileStackScreen} />
    </Tabs.Navigator>
  );
};

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator headerMode="false" initialRouteName="LoginScreen">
        <Stack.Screen name="LoginScreen" component={LoginScreen} />
        <Stack.Screen name="SearchScreen" component={SearchScreen} />
        <Stack.Screen name="InstSearchScreen" component={InstSearchScreen} />
        <Stack.Screen name="CameraScreen" component={CameraScreen} />
        <Stack.Screen name="UserScreens" component={UserScreens} />
        <Stack.Screen name="InstScreens" component={InstScreens} />
        <Stack.Screen name="MapScreen" component={MapScreen} />
        <Stack.Screen name="InstMapScreen" component={InstMapScreen} />
        <Stack.Screen name="EmployeeScreen" component={EmployeeScreen} />
        <Stack.Screen name="ProfileScreen" component={ProfileScreen} />
        <Stack.Screen name="InstProfileScreen" component={InstProfileScreen} />
        <Stack.Screen name="PostScreen" component={PostScreen} />
        <Stack.Screen name="FeedScreen" component={FeedScreen}/>
        <Stack.Screen name="InstFeedScreen" component={InstFeedScreen} />
        <Stack.Screen name="WelcomeScreen" component={WelcomeScreen} />
        <Stack.Screen
          name="RegisterChooseScreen"
          component={RegisterChooseScreen}
        />
        <Stack.Screen
          name="CitizenRegisterScreen"
          component={CitizenRegisterScreen}
        />
        <Stack.Screen
          name="InstitutionRegisterScreen"
          component={InstitutionRegisterScreen}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
