package com.jcity.pipeline;

import java.util.*;

import org.apache.commons.chain.impl.*;
import org.apache.commons.logging.*;

import com.jcity.model.*;

@SuppressWarnings("serial")
public class CityContext extends ContextBase {

	private Log logger = LogFactory.getLog(this.getClass());
	private String projectName;

	/* Config properties */
	private String heightMapResourceName, densityMapResourceName, waterMapResourceName, patternMapResourceName, blockedMapResourceName, buildingHeightMapResourceName, inputDir, outputDir;

	private double resolutionX, resolutionY;
	private int quadTreeLevels;
	private double defaultPopulationDensity;

	private double initialRoadSegmentStartX, initialRoadSegmentStartY, initialRoadSegmentEndX, initialRoadSegmentEndY;
	private double roadLaneWidth, roadMaxBranches, roadBranchProbability, roadSegmentLength, roadMaxShortening, roadSamplingLength, roadMaxTurningAngle, roadMaxGradient, roadSearchRadius;
	private double terrainMinHeight, terrainMaxHeight;
	private boolean isRoadDensitySensitive, isRoadPatternSensitive;
	private int roadBranchDelay, roadSamplingRate;

	private int manhattenPatternColorKey, sanFranciscoPatternColorKey;
	private boolean debug;

	/* Actually useful stuff. */
	private Layer heightMap;
	private Layer waterMap;
	private Layer blockedMap;
	private Layer patternMap;
	private Layer densityMap;
	private Layer buildingHeightMap;

	private long randomSeed = System.currentTimeMillis();

	private Random random;
	
	public void randomize() {
		this.random = new Random(System.currentTimeMillis());
	}

	public void randomize(long seed) {
		this.randomSeed = seed;
		this.random = new Random(seed);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHeightMapResourceName() {
		return heightMapResourceName;
	}

	public void setHeightMapResourceName(String heightMapResourceName) {
		this.heightMapResourceName = heightMapResourceName;
	}

	public String getDensityMapResourceName() {
		return densityMapResourceName;
	}

	public void setDensityMapResourceName(String densityMapResourceName) {
		this.densityMapResourceName = densityMapResourceName;
	}

	public String getWaterMapResourceName() {
		return waterMapResourceName;
	}

	public void setWaterMapResourceName(String waterMapResourceName) {
		this.waterMapResourceName = waterMapResourceName;
	}

	public String getPatternMapResourceName() {
		return patternMapResourceName;
	}

	public void setPatternMapResourceName(String patternMapResourceName) {
		this.patternMapResourceName = patternMapResourceName;
	}

	public String getBlockedMapResourceName() {
		return blockedMapResourceName;
	}

	public void setBlockedMapResourceName(String blockedMapResourceName) {
		this.blockedMapResourceName = blockedMapResourceName;
	}

	public String getBuildingHeightMapResourceName() {
		return buildingHeightMapResourceName;
	}

	public void setBuildingHeightMapResourceName(String buildingHeightMapResourceName) {
		this.buildingHeightMapResourceName = buildingHeightMapResourceName;
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public double getResolutionX() {
		// TODO: Resolution in what? Are these world coords? Render coords?
		// Meters? Pixels? WTF are these?
		return resolutionX;
	}

	public void setResolutionX(double resolutionX) {
		this.resolutionX = resolutionX;
	}

	public double getResolutionY() {
		return resolutionY;
	}

	public void setResolutionY(double resolutionY) {
		this.resolutionY = resolutionY;
	}

	public int getQuadTreeLevels() {
		return quadTreeLevels;
	}

	public void setQuadTreeLevels(int quadTreeLevels) {
		this.quadTreeLevels = quadTreeLevels;
	}

	public double getDefaultPopulationDensity() {
		return defaultPopulationDensity;
	}

	public void setDefaultPopulationDensity(double defaultPopulationDensity) {
		this.defaultPopulationDensity = defaultPopulationDensity;
	}

	public double getInitialRoadSegmentStartX() {
		return initialRoadSegmentStartX;
	}

	public void setInitialRoadSegmentStartX(double initialRoadSegmentStartX) {
		this.initialRoadSegmentStartX = initialRoadSegmentStartX;
	}

	public double getInitialRoadSegmentStartY() {
		return initialRoadSegmentStartY;
	}

	public void setInitialRoadSegmentStartY(double initialRoadSegmentStartY) {
		this.initialRoadSegmentStartY = initialRoadSegmentStartY;
	}

	public double getInitialRoadSegmentEndX() {
		return initialRoadSegmentEndX;
	}

	public void setInitialRoadSegmentEndX(double initialRoadSegmentEndX) {
		this.initialRoadSegmentEndX = initialRoadSegmentEndX;
	}

	public double getInitialRoadSegmentEndY() {
		return initialRoadSegmentEndY;
	}

	public void setInitialRoadSegmentEndY(double initialRoadSegmentEndY) {
		this.initialRoadSegmentEndY = initialRoadSegmentEndY;
	}

	public double getRoadLaneWidth() {
		return roadLaneWidth;
	}

	public void setRoadLaneWidth(double roadLaneWidth) {
		this.roadLaneWidth = roadLaneWidth;
	}

	public double getRoadMaxBranches() {
		return roadMaxBranches;
	}

	public void setRoadMaxBranches(double roadMaxBranches) {
		this.roadMaxBranches = roadMaxBranches;
	}

	public double getRoadBranchProbability() {
		return roadBranchProbability;
	}

	public void setRoadBranchProbability(double roadBranchProbability) {
		this.roadBranchProbability = roadBranchProbability;
	}

	public double getRoadSegmentLength() {
		return roadSegmentLength;
	}

	public void setRoadSegmentLength(double roadSegmentLength) {
		this.roadSegmentLength = roadSegmentLength;
	}

	public double getRoadMaxShortening() {
		return roadMaxShortening;
	}

	public void setRoadMaxShortening(double roadMaxShortening) {
		this.roadMaxShortening = roadMaxShortening;
	}

	public double getRoadSamplingLength() {
		return roadSamplingLength;
	}

	public void setRoadSamplingLength(double roadSamplingLength) {
		this.roadSamplingLength = roadSamplingLength;
	}

	public double getRoadMaxTurningAngle() {
		return roadMaxTurningAngle;
	}

	public void setRoadMaxTurningAngle(double roadMaxTurningAngle) {
		this.roadMaxTurningAngle = roadMaxTurningAngle;
	}

	public double getRoadMaxGradient() {
		return roadMaxGradient;
	}

	public void setRoadMaxGradient(double roadMaxGradient) {
		this.roadMaxGradient = roadMaxGradient;
	}

	public double getRoadSearchRadius() {
		return roadSearchRadius;
	}

	public void setRoadSearchRadius(double roadSearchRadius) {
		this.roadSearchRadius = roadSearchRadius;
	}

	public boolean isRoadDensitySensitive() {
		return isRoadDensitySensitive;
	}

	public void setRoadDensitySensitive(boolean isRoadDensitySensitive) {
		this.isRoadDensitySensitive = isRoadDensitySensitive;
	}

	public boolean isRoadPatternSensitive() {
		return isRoadPatternSensitive;
	}

	public void setRoadPatternSensitive(boolean isRoadPatternSensitive) {
		this.isRoadPatternSensitive = isRoadPatternSensitive;
	}

	public int getRoadBranchDelay() {
		return roadBranchDelay;
	}

	public void setRoadBranchDelay(int roadBranchDelay) {
		this.roadBranchDelay = roadBranchDelay;
	}

	public int getRoadSamplingRate() {
		return roadSamplingRate;
	}

	public void setRoadSamplingRate(int roadSamplingRate) {
		this.roadSamplingRate = roadSamplingRate;
	}

	public int getManhattenPatternColorKey() {
		return manhattenPatternColorKey;
	}

	public void setManhattenPatternColorKey(int manhattenPatternColorKey) {
		this.manhattenPatternColorKey = manhattenPatternColorKey;
	}

	public int getSanFranciscoPatternColorKey() {
		return sanFranciscoPatternColorKey;
	}

	public void setSanFranciscoPatternColorKey(int sanFranciscoPatternColorKey) {
		this.sanFranciscoPatternColorKey = sanFranciscoPatternColorKey;
	}

	public Layer getHeightMap() {
		return heightMap;
	}

	public void setHeightMap(Layer heightMap) {
		this.heightMap = heightMap;
	}

	public Layer getWaterMap() {
		return waterMap;
	}

	public void setWaterMap(Layer waterMap) {
		this.waterMap = waterMap;
	}

	public Layer getBlockedMap() {
		return blockedMap;
	}

	public void setBlockedMap(Layer blockedMap) {
		this.blockedMap = blockedMap;
	}

	public Layer getPatternMap() {
		return patternMap;
	}

	public void setPatternMap(Layer patternMap) {
		this.patternMap = patternMap;
	}

	public Layer getDensityMap() {
		return densityMap;
	}

	public void setDensityMap(Layer densityMap) {
		this.densityMap = densityMap;
	}

	public Layer getBuildingHeightMap() {
		return buildingHeightMap;
	}

	public void setBuildingHeightMap(Layer buildingHeightMap) {
		this.buildingHeightMap = buildingHeightMap;
	}

	public long getRandomSeed() {
		return randomSeed;
	}

	public void setRandomSeed(long randomSeed) {
		this.randomSeed = randomSeed;
		randomize(randomSeed); // Is this the right place for this?
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public double getTerrainMinHeight() {
		return terrainMinHeight;
	}

	public void setTerrainMinHeight(double terrainMinHeight) {
		this.terrainMinHeight = terrainMinHeight;
	}

	public double getTerrainMaxHeight() {
		return terrainMaxHeight;
	}

	public void setTerrainMaxHeight(double terrainMaxHeight) {
		this.terrainMaxHeight = terrainMaxHeight;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
