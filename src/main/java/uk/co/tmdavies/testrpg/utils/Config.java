package uk.co.tmdavies.testrpg.utils;

import org.bspfsystems.yamlconfiguration.configuration.InvalidConfigurationException;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

	File file;
	YamlConfiguration config;

	public Config(String name) {

		this.file = new File(System.getProperty("user.dir") + "\\TestRPG\\" + name + ".yml");

		if (!this.file.getParentFile().exists()) {

			this.file.getParentFile().mkdirs();

		}

		if (!this.file.exists()) {

			try { this.file.createNewFile(); } catch (IOException e) {

				System.err.println("Error creating Config File");
				e.printStackTrace();

			}

		}

		this.config = YamlConfiguration.loadConfiguration(this.file);

	}

	public File getFile() {

		return this.file;

	}

	public YamlConfiguration getConfig() {

		return this.config;

	}

	public void set(String path, Object value) {

		this.config.set(path, value);

	}

	public void saveConfig() {

		try {

			this.config.save(this.file);

			this.reloadConfig();

		} catch (IOException e) {

			System.err.println("Error saving Config File.");
			e.printStackTrace();

		}

	}

	public void reloadConfig() {

		try {

			this.config.load(this.file);

		} catch (IOException | InvalidConfigurationException e) {

			System.err.println("Error loading Config File.");
			e.printStackTrace();

		}

	}
}
